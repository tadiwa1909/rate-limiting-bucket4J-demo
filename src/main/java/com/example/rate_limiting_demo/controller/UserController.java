package com.example.rate_limiting_demo.controller;

import com.example.rate_limiting_demo.dto.UserPatchDto;
import com.example.rate_limiting_demo.dto.UserRequest;
import com.example.rate_limiting_demo.dto.UserResponse;
import com.example.rate_limiting_demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@NullMarked
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users", version = "1.0")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request){
        logger.info("Request for {}. Method {}", request.getRequestURI(), request.getMethod());
        UserResponse userResponse = userService.save(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}", version = "1.0")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id, HttpServletRequest request){
        logger.info("Request for {}. Method {}", request.getRequestURI(), request.getMethod());
        UserResponse userResponse = userService.getUser(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/users", version = "1.0")
    public ResponseEntity<Page<UserResponse>> getUsers(@RequestParam("limit") int limit, @RequestParam("offset") int offset, HttpServletRequest request){
        logger.info("Request for {}. Method {}", request.getRequestURI(), request.getMethod());
        Page<UserResponse> userResponsePage = userService.getUsers(limit, offset);
        if (userResponsePage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userResponsePage, HttpStatus.OK);
    }

    @PatchMapping(value = "/users/{id}", version = "1.0")
    public ResponseEntity<UserResponse> patchUser(@Valid @RequestBody UserPatchDto userPatchDto,
                                                  @PathVariable("id") Long id, HttpServletRequest request){
        logger.info("Request for {}. Method {}", request.getRequestURI(), request.getMethod());
        UserResponse userResponse = userService.patchUser(id, userPatchDto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}", version = "1.0")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id, HttpServletRequest request){
        logger.info("Request for {}. Method {}", request.getRequestURI(), request.getMethod());
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
    }

}
