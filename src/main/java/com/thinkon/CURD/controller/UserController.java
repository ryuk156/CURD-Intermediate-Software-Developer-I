package com.thinkon.CURD.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinkon.CURD.model.User;
import com.thinkon.CURD.repo.UserRepo;

@RestController
public class UserController {
    
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllusers() {
        try{

            List<User> users = new ArrayList<>();
            userRepo.findAll().forEach(users::add);

            if(users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try{
            Optional<User> user = userRepo.findById(id);
            if(!user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userObject = userRepo.save(user);

      return new ResponseEntity<>(userObject, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById( @PathVariable Long id, @RequestBody User updatedUserData) {
        Optional<User> existingUserOptional = userRepo.findById(id);
    
        
        if (existingUserOptional.isPresent()) {
            
            User existingUser = existingUserOptional.get();
            
            // Update the fields
            existingUser.setUsername(updatedUserData.getUsername());
            existingUser.setFirstname(updatedUserData.getFirstname());
            existingUser.setLastname(updatedUserData.getLastname());
            existingUser.setEmail(updatedUserData.getEmail());
            existingUser.setPhonenumber(updatedUserData.getPhonenumber());
    
            // Save the updated user
            User updatedUser = userRepo.save(existingUser);
    
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
        userRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
