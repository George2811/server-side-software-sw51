package com.acme.perustars.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DefaultUserDetailsService extends UserDetailsService {
    List<User> getAll();
}
