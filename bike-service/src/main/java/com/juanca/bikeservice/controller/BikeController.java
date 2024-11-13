package com.juanca.bikeservice.controller;

import com.juanca.bikeservice.entity.Bike;
import com.juanca.bikeservice.service.BikeService;
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
@RequestMapping("/api/v1")
public class BikeController {
    
     @Autowired
    private BikeService bikeService;
    
    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> listarBikes()
    {
        List<Bike> listBikes = bikeService.listBikes();
        
        if(listBikes.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(listBikes);
    }
    
    
    @GetMapping("/bike/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable int id)
    {
        Bike searchBike = bikeService.getBike(id);
        
        if(searchBike==null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(searchBike);
    }
    
    
    @PostMapping("/bike")
    public ResponseEntity<Bike> insertBike(@RequestBody Bike bike)
    {
        Bike newbike = bikeService.insertBike(bike);
        
        return ResponseEntity.ok(newbike);
    }
    
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> listarBikesByUserId(@PathVariable int userId)
    {
        List<Bike> listBikes = bikeService.byUserId(userId);
        
        if(listBikes.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(listBikes);
    }
    
    
}
