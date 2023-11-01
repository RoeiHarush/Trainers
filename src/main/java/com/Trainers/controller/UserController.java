package com.Trainers.controller;

import com.Trainers.model.User;
import com.Trainers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/get")
    public ResponseEntity<List<User>> findAll()
    {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);

    }



    @GetMapping("/get/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id)
    {
        if (userService.findById(id).isPresent())
        {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }



    }


}
