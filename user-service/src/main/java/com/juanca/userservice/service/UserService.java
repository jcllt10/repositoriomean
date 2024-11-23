package com.juanca.userservice.service;

import com.juanca.userservice.entity.User;
import com.juanca.userservice.feignClients.BikeFeignClient;
import com.juanca.userservice.feignClients.CarFeignClient;
import com.juanca.userservice.respository.IUserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Bike;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private CarFeignClient carFeignClient;
    
    @Autowired
    private BikeFeignClient bikeFeignClient;
    
    public List<User> listUsers()
    {
     return userRepository.findAll();
    }
    
    public User getUserById(int idUser)
    {
       return userRepository.findById(idUser).orElse(null);
    }
    
    public User insertUser(User newUser)
    {
      return userRepository.save(newUser);
    }
    
     public List<Car> getCars(int idUser)
    {
      List<Car> carsByUser = restTemplate.getForObject("http://car-service/cr/cars/" + idUser, List.class);
      
      return carsByUser;
    }
    
    public List<Bike> getBikes(int idUser)
    {
      List<Bike> bikesByUser = restTemplate.getForObject("http://bike-service/bk/bikes/" + idUser, List.class);
       
       return bikesByUser;
    }
    
    public Car guardarCar(Car car,int idUser)
    {
        car.setUserId(idUser);
        Car carNew = carFeignClient.insertCar(car);
        
        return carNew;
    }
    
    public Bike guardarBike(Bike bike,int idUser)
    {
       bike.setUserId(idUser);
       Bike bikeNew = bikeFeignClient.insertBike(bike);
       
       return bikeNew;
    }
    
   public Map<String,Object> getUserAndVehicles(int userId)
    {
        Map<String,Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        
        if(user == null){
             result.put("mensaje", "No existe el usuario enviado");
             return result;
        }
        result.put("User", user);
        
        List<Car> listCars = carFeignClient.listarCarsByUserId(userId);
        if(listCars==null)
            result.put("cars", "El usuario no tiene coches registrados");
        else
            result.put("cars",listCars);
        
        List<Bike> ListBikes = bikeFeignClient.listarBikesByUserId(userId);
        if(ListBikes==null)
            result.put("bikes", "El usuario no tiene bicicletas registradas");
        else
            result.put("bikes",ListBikes);
        
        return result;
    }
    
}
