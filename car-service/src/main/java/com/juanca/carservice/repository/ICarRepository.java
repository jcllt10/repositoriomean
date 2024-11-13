package com.juanca.carservice.repository;

import com.juanca.carservice.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car,Integer>{
    
    public List<Car> findByUserId(int userId);
    
}
