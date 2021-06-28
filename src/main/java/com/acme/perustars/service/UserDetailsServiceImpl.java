package com.acme.perustars.service;

import com.acme.perustars.domain.model.User;
import com.acme.perustars.domain.repository.UserRepository;
import com.acme.perustars.domain.service.DefaultUserDetailsService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements DefaultUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();

        if (user == null){
            throw new UsernameNotFoundException("User not found with username " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                null
        );
    }

    public List<org.springframework.security.core.userdetails.User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .getContent().stream().map(user -> {
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            passwordEncoder.encode(user.getPassword()),
                            null
                    );
                }).collect(Collectors.toList());
    }
}
