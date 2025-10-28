package com.example.rate_limiting_demo.mapper;

import com.example.rate_limiting_demo.dto.UserRequest;
import com.example.rate_limiting_demo.dto.UserResponse;
import com.example.rate_limiting_demo.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static User mapUserRequestToUser(@NonNull UserRequest userRequest){
        return new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getRole()
        );
    }

    public static UserResponse mapUserResponseFromUser(@NonNull User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
