package com.hungfnguyen.identity_service.controller;

import com.hungfnguyen.identity_service.dto.request.ApiReponse;
import com.hungfnguyen.identity_service.dto.request.UserCreationRequest;
import com.hungfnguyen.identity_service.dto.request.UserUpdateRequest;
import com.hungfnguyen.identity_service.entity.User;
import com.hungfnguyen.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiReponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiReponse<User> apiReponse = new ApiReponse<User>();
        apiReponse.setResult(userService.createUser(request));
        return apiReponse;
    }

    @GetMapping
    List<User> getUser(){
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId ,@RequestBody @Valid UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
