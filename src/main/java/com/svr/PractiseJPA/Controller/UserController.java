package com.svr.PractiseJPA.Controller;

import com.svr.PractiseJPA.DTO.UserRequest;
import com.svr.PractiseJPA.DTO.UserResponse;
import com.svr.PractiseJPA.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> allUsers = userService.getAllUsers();
        return allUsers.isEmpty() ? ResponseEntity.badRequest().body(new ArrayList<>()) : ResponseEntity.ok().header("All Users").body(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getAllUsers(@PathVariable(name = "id") int userId) {
        UserResponse singleUser = userService.getSingleUser(userId);
        return singleUser == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().header("All Users").body(singleUser);
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest user) {
        UserResponse userResponse = userService.addUser(user);
        return userResponse == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().header("All Users").body(userResponse);
    }

    @PutMapping("/replace")
    public ResponseEntity<UserResponse> replceUser(@RequestParam int id, @RequestBody @Valid UserRequest user) {
        UserResponse userResponse = userService.replaceUserByID(id, user);
        return userResponse == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().header("All Users").body(userResponse);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@RequestParam int id, @RequestBody @Valid UserRequest user) {
        UserResponse userResponse = userService.updateUserByID(id, user);
        return userResponse == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().header("All Users").body(userResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable int id) {
        UserResponse userResponse = userService.deleteUserByUserId(id);
        return userResponse == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().header("All Users").body(userResponse);
    }
}
