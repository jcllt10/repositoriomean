package com.juanca.carservice.service;

import com.juanca.carservice.entity.Car;
import com.juanca.carservice.repository.ICarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    
    @Autowired
    private ICarRepository carRepository;
    
    public List<Car> listCars()
    {
       return carRepository.findAll();
    }
    
    public Car getCar(int idCar)
    {
       return carRepository.findById(idCar).orElse(null);
    }
    
    public Car insertCar(Car newCar)
    {
      return carRepository.save(newCar);
    }
    
    public List<Car> byUserId(int userId)
    {
      return carRepository.findByUserId(userId);
    }
    
}
