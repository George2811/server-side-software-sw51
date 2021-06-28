package com.acme.perustars.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DefaultUserDetailsService extends UserDetailsService {
    Page<User> getAll(Pageable pageable);
}
