package com.juanca.userservice.feignClients;

import java.util.List;
import model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "bike-service",url = "http://localhost:8023/api/v1")
@FeignClient(name = "bike-service")
//@RequestMapping("/api/v1")
public interface BikeFeignClient {
    
    @PostMapping("/bike")
    Bike insertBike(@RequestBody Bike bike);
    
    @GetMapping("/bikes/{userId}")
    List<Bike> listarBikesByUserId(@PathVariable("userId") int userId);
}
