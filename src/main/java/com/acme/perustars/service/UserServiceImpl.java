package com.acme.perustars.service;

import com.acme.perustars.domain.model.Specialty;
import com.acme.perustars.domain.model.User;
import com.acme.perustars.domain.repository.UserRepository;
import com.acme.perustars.domain.service.UserService;
import com.acme.perustars.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User",
                "Username", username));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("There is already another user with same username");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id",
                userId));

        if (userRequest.getUsername() !=  user.getUsername()){
            if (userRepository.existsByUsername(userRequest.getUsername()))
                throw new RuntimeException("There is already another user with same username");
        }

        user.setUsername(userRequest.getUsername())
                .setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id",
                userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User",
                "Username", username));

        if (user == null)
            throw new UsernameNotFoundException("User not found with username " + username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), DEFAULT_AUTHORITIES);
    }

    @Override
    public void InitializeUsers() {
        if (userRepository.findAll().size() <= 0){
            userRepository.save(new User().setUsername("hola@hotmail.com").setPassword(passwordEncoder.encode("siuu")));
            System.out.printf("User added");
            return;
        }
        System.out.printf("There are already a user added");
    }
}
