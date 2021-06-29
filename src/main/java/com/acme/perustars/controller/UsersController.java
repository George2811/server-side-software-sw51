package com.acme.perustars.controller;

import com.acme.perustars.domain.model.User;
import com.acme.perustars.domain.service.UserService;
import com.acme.perustars.resource.UserResource;
import com.acme.perustars.service.communication.AuthenticationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;



    @Operation(summary = "Get All Users", description = "Get All Users by Pages", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Register User", description = "Register a User", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping
    public UserResource registerUser(@RequestBody AuthenticationRequest request) {
        User user = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        return convertToResource(userService.createUser(user));
    }



    @Operation(summary = "Register User", description = "Register a User", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody AuthenticationRequest request) {
        User user = new User().setUsername(request.getUsername()).setPassword(request.getPassword());
        return convertToResource(userService.updateUser(userId, user));
    }

    private UserResource convertToResource(User user) {
        return mapper.map(user, UserResource.class);
    }
}
