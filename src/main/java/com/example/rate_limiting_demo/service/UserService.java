package com.example.rate_limiting_demo.service;

import com.example.rate_limiting_demo.dto.UserPatchDto;
import com.example.rate_limiting_demo.dto.UserRequest;
import com.example.rate_limiting_demo.dto.UserResponse;
import com.example.rate_limiting_demo.exception.ResourceNotFoundException;
import com.example.rate_limiting_demo.mapper.UserMapper;
import com.example.rate_limiting_demo.model.User;
import com.example.rate_limiting_demo.exception.ResourceExistsException;
import com.example.rate_limiting_demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@NullMarked
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse save(@Valid UserRequest userRequest) {
        logger.info("Saving new user record for {}", userRequest.getUsername());
        if (userRepository.existsByEmail(userRequest.getEmail())){
            logger.warn("Failed to save user record. User email is already taken");
            throw new ResourceExistsException("Email already exists");
        }
        if (userRepository.existsByUsername(userRequest.getUsername())){
            logger.warn("Failed to save user record. Username is already taken");
            throw new ResourceExistsException("Username already exists");
        }
        User user = UserMapper.mapUserRequestToUser(userRequest);
        userRepository.save(user);
        logger.info("User {} saved successfully", userRequest.getUsername());
        return UserMapper.mapUserResponseFromUser(user);
    }

    public UserResponse getUser(Long id) {
        logger.info("Fetching user {} from the database", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        logger.info("User {} found", id);
        return UserMapper.mapUserResponseFromUser(user);
    }

    public Page<UserResponse> getUsers(int limit, int offset) {
        if (limit < 0 || offset <= 0){
            throw new IllegalArgumentException("Limit cannot be negative and Offset must be greater than zero");
        }
        logger.info("Fetching page {} of users", offset);
        Pageable pageable = PageRequest.of(limit, offset);
        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole()));
    }


    public UserResponse patchUser(Long id, @Valid UserPatchDto userPatchDto) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        logger.info("Attempting to patch user {}", user.getUsername());
        if (userPatchDto.getPassword() != null){
            user.setPassword(user.getPassword());
        }
        if (userPatchDto.getRole() != null){
            user.setRole(userPatchDto.getRole());
        }
        userRepository.save(user);
        logger.info("User updated successfully");
        return UserMapper.mapUserResponseFromUser(user);
    }

    public void deleteUser(Long id) {
        logger.info("Attempting to delete user {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
        logger.info("User has been deleted successfully");
    }
}
