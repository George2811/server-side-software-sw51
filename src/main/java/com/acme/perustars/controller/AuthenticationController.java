package com.acme.perustars.controller;

import com.acme.perustars.domain.model.User;
import com.acme.perustars.domain.service.UserService;
import com.acme.perustars.service.communication.AuthenticationRequest;
import com.acme.perustars.service.communication.AuthenticationResponse;
import com.acme.perustars.util.JwtCenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCenter tokenCenter;
    @Autowired
    private UserService userService;


    @Operation(summary = "Authenticate User", description = "Sign in and authentication a user", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users authenticated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final User user = userService.getUserByUsername(request.getUsername());
        String token = tokenCenter.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse().setUsername(user.getUsername()).setToken(token).setUserId(user.getId()));
    }


    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
