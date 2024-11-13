package com.juanca.bikeservice.repository;

import com.juanca.bikeservice.entity.Bike;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBikeRepository extends JpaRepository<Bike, Integer>{
    
    public List<Bike> findByUserId(int userId);
    
}
