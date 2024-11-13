package com.juanca.bikeservice.service;

import com.juanca.bikeservice.entity.Bike;
import com.juanca.bikeservice.repository.IBikeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeService {
    
    @Autowired
    private IBikeRepository bikeRepository;
    
    public List<Bike> listBikes()
    {
       return bikeRepository.findAll();
    }
    
    public Bike getBike(int idBike)
    {
       return bikeRepository.findById(idBike).orElse(null);
    }
    
    public Bike insertBike(Bike newBike)
    {
      return bikeRepository.save(newBike);
    }
    
    public List<Bike> byUserId(int userId)
    {
      return bikeRepository.findByUserId(userId);
    }
    
}
