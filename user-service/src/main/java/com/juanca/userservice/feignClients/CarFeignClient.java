package com.juanca.userservice.feignClients;

import java.util.List;
import model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "car-service",url = "http://localhost:8022/api/v1")
//@RequestMapping("/api/v1")
public interface CarFeignClient {
    
    @PostMapping("/car")
    Car insertCar(@RequestBody Car car);
    
    @GetMapping("/cars/{userId}")
    List<Car> listarCarsByUserId(@PathVariable("userId") int userId);
    
}
