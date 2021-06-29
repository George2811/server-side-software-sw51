package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    User createUser(User user);
    User updateUser(Long userId, User userRequest);
    ResponseEntity<?> deleteUser(Long userId);
    void InitializeUsers();
}
