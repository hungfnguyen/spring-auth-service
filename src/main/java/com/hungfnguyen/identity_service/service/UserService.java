package com.hungfnguyen.identity_service.service;

import com.hungfnguyen.identity_service.dto.request.UserCreationRequest;
import com.hungfnguyen.identity_service.dto.request.UserUpdateRequest;
import com.hungfnguyen.identity_service.dto.response.UserResponse;
import com.hungfnguyen.identity_service.entity.User;
import com.hungfnguyen.identity_service.exception.AppException;
import com.hungfnguyen.identity_service.exception.ErrorCode;
import com.hungfnguyen.identity_service.mapper.UserMapper;
import com.hungfnguyen.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;

    public User createUser(UserCreationRequest request)
    {
        if(userRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<UserResponse> getUser(){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUser(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        //Optional<User> user = userRepository.findById(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
