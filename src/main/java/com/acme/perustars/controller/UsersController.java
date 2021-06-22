package com.acme.perustars.controller;

import com.acme.perustars.domain.service.DefaultUserDetailsService;
import com.acme.perustars.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private ModelMapper mapper;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @GetMapping
    public List<UserResource> getAll() {
        return userDetailsService.getAll().stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
    }
    private UserResource convertToResource(User user) {
        return mapper.map(user, UserResource.class);
    }
}
