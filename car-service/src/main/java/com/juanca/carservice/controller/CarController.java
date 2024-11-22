package com.juanca.carservice.controller;

import com.juanca.carservice.entity.Car;
import com.juanca.carservice.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1")
public class CarController {
    
    @Autowired
    private CarService carService;
    
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listarCars()
    {
        List<Car> listCars = carService.listCars();
        
        if(listCars.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(listCars);
    }
    
    
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id)
    {
        Car searchCar = carService.getCar(id);
        
        if(searchCar==null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(searchCar);
    }
    
    
    @PostMapping("/car")
    public ResponseEntity<Car> insertCar(@RequestBody Car car)
    {
        Car newcar = carService.insertCar(car);
        
        return ResponseEntity.ok(newcar);
    }
    
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> listarCarsByUserId(@PathVariable int userId)
    {
        List<Car> listCars = carService.byUserId(userId);
        
        if(listCars.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(listCars);
    }
    
}
