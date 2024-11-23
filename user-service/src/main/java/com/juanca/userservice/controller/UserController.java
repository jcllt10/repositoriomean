package com.juanca.userservice.controller;

import com.juanca.userservice.entity.User;
import com.juanca.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Map;
import model.Bike;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/usr/users")
    public ResponseEntity<List<User>> listarUsers()
    {
        List<User> listaUsers = userService.listUsers();
        
        if(listaUsers.isEmpty())
        {
           return ResponseEntity.noContent().build();
        }
        
         return ResponseEntity.ok(listaUsers);
    }
    
    @GetMapping("/usr/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id)
    {
        User searchUser = userService.getUserById(id);
        
        if(searchUser==null)
        {
           return ResponseEntity.notFound().build();
        }
        
         return ResponseEntity.ok(searchUser);
    }
    
    @PostMapping("/usr/user")
    public ResponseEntity<User> registrarUser(@RequestBody User user)
    {
        User newUser = userService.insertUser(user);
        
        return ResponseEntity.ok(newUser);
    }
    
    //el parametro fallbackMethod es el metodo que se llamara en caso este metodo(listarCarsPorIdUser) falle
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping("/usr/user/cars/{idUser}")
    public ResponseEntity<List<Car>> listarCarsPorIdUser(@PathVariable("idUser") int idUser)
    {
       User user = userService.getUserById(idUser);
       
       if(user == null)
          return ResponseEntity.notFound().build();
       
       List<Car> cars = userService.getCars(idUser);
       
       if(cars==null)
           return ResponseEntity.notFound().build();
       
       return ResponseEntity.ok(cars);
    }
    
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
    @PostMapping("/usr/user/savecar/{idUser}")
    public ResponseEntity<Car> guardarCar(@RequestBody Car car,@PathVariable("idUser") int idUser)
    {
       if(userService.getUserById(idUser)==null)
           return ResponseEntity.notFound().build();
        
       Car carNew = userService.guardarCar(car, idUser);
       
       return ResponseEntity.ok(carNew);
    }
    
    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackGetBikes")
    @GetMapping("/usr/user/bikes/{idUser}")
    public ResponseEntity<List<Bike>> listarBikesPorIdUser(@PathVariable("idUser") int idUser)
    {
       User user = userService.getUserById(idUser);
       
       if(user == null)
          return ResponseEntity.notFound().build();
       
       List<Bike> bikes = userService.getBikes(idUser);
       
       if(bikes==null)
           return ResponseEntity.notFound().build();
       
       return ResponseEntity.ok(bikes);
    }
    
    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveBike")
    @PostMapping("/usr/user/savebike/{idUser}")
    public ResponseEntity<Bike> guardarBike(@RequestBody Bike bike,@PathVariable("idUser") int idUser)
    {
       if(userService.getUserById(idUser)==null)
           return ResponseEntity.notFound().build();
        
       Bike bikeNew = userService.guardarBike(bike,idUser);
       
       return ResponseEntity.ok(bikeNew);
    }
    
    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
    @GetMapping("/usr/user/getAll/{userId}")
    public ResponseEntity<Map<String,Object>> listarTodosLosVehiculosPorIdUser(@PathVariable("userId") int idUser)
    {
       Map<String,Object> resultAllVehiculos = userService.getUserAndVehicles(idUser);
       
       return ResponseEntity.ok(resultAllVehiculos);
    }
    
    //metodos que se llamaran en caso un metodo falle
    
    private ResponseEntity<List<Car>> fallBackGetCars(@PathVariable("idUser") int idUser, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " tiene los coches en el taller", HttpStatus.OK);
    }
    
    private ResponseEntity<Car> fallBackSaveCar(@PathVariable("idUser") int idUser, @RequestBody Car car, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " no tiene dinero para coches", HttpStatus.OK);
    }
    
     private ResponseEntity<List<Bike>> fallBackGetBikes(@PathVariable("idUser") int idUser, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Car> fallBackSaveBike(@PathVariable("idUser") int idUser, @RequestBody Bike bike, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + "  no tiene dinero para motos", HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> fallBackGetAll(@PathVariable("userId") int idUser, RuntimeException e) {
        return new ResponseEntity("El usuario " + idUser + " tiene los vehículos en el taller", HttpStatus.OK);
    }
}
