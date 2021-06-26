package com.acme.perustars.service;

//import com.acme.perustars.domain.repository.UserRepository;
import com.acme.perustars.domain.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements DefaultUserDetailsService {
    private static final String DEFAULT_USERNAME = "john.doe@gmail.com";
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    //@Autowired
    //private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Implement Repository-based User Store
        String defaultPassword = passwordEncoder.encode("password");
        //User user = userRepository.findByUsername(username);

        //if(!user.getUsername().isBlank()) {
            //return new User(DEFAULT_USERNAME, defaultPassword, DEFAULT_AUTHORITIES);
        //}
        if(DEFAULT_USERNAME.equals(username)) {
            return new User(DEFAULT_USERNAME, defaultPassword, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException("User not found with username " + username);
    }

    public List<User> getAll(Pageable pageable) {
        //return userRepository.findAll(pageable);
        return Arrays.asList(
                new User("john.doe@gmail.com",
                        passwordEncoder.encode("password"),
                        DEFAULT_AUTHORITIES),
                new User("jason.bourne@treatstone.gov",
                        passwordEncoder.encode("easy-one"),
                        DEFAULT_AUTHORITIES)
        );
    }
}
