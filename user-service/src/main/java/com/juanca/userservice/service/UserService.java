package com.juanca.userservice.service;

import com.juanca.userservice.entity.User;
import com.juanca.userservice.respository.IUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepository;
    
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
    
}
