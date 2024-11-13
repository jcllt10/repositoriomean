package com.juanca.userservice.service;

import com.juanca.userservice.entity.User;
import com.juanca.userservice.respository.IUserRepository;
import java.util.List;
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
      List<Car> carsByUser = restTemplate.getForObject("http://localhost:8022/api/v1/cars/" + idUser, List.class);
      
      return carsByUser;
    }
    
    public List<Bike> getBikes(int idUser)
    {
      List<Bike> bikesByUser = restTemplate.getForObject("http://localhost:8023/api/v1/bikes/" + idUser, List.class);
       
       return bikesByUser;
    }
    
}
