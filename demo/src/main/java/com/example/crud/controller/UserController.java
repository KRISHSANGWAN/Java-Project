package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existing = service.get(id);
        if (existing != null) {
            existing.setName(updatedUser.getName());
            existing.setEmail(updatedUser.getEmail());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }
}
