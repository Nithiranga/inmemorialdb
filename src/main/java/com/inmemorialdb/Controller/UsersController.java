package com.inmemorialdb.Controller;

import com.inmemorialdb.model.Users;
import com.inmemorialdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
@Autowired
     UserRepository userRepository;
@GetMapping("/Users")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/Users/{id}")
    private Users getUsersById(@PathVariable("id")int id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/Users")
    private ResponseEntity createUsers(@RequestBody Users users) {
        try {
            userRepository.save(users);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("New Users created with id: " + users.getId(), HttpStatus.CREATED);
    }
    @DeleteMapping("/Users/{id}")
    private ResponseEntity deleteById(@PathVariable("id") int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("User deleted with id: " + id, HttpStatus.OK);
    }

}
