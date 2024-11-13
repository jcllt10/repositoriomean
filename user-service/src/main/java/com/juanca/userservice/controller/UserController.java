package com.juanca.userservice.controller;

import com.juanca.userservice.entity.User;
import com.juanca.userservice.service.UserService;
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
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> listarUsers()
    {
        List<User> listaUsers = userService.listUsers();
        
        if(listaUsers.isEmpty())
        {
           return ResponseEntity.noContent().build();
        }
        
         return ResponseEntity.ok(listaUsers);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id)
    {
        User searchUser = userService.getUserById(id);
        
        if(searchUser==null)
        {
           return ResponseEntity.notFound().build();
        }
        
         return ResponseEntity.ok(searchUser);
    }
    
    @PostMapping("/user")
    public ResponseEntity<User> registrarUser(@RequestBody User user)
    {
        User newUser = userService.insertUser(user);
        
        return ResponseEntity.ok(newUser);
    }
    
}
