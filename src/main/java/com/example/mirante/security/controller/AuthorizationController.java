package com.example.mirante.security.controller;

import com.example.mirante.security.model.AuthenticationRequest;
import com.example.mirante.security.model.AuthenticationResponse;
import com.example.mirante.security.model.Role;
import com.example.mirante.security.model.User;
import com.example.mirante.security.repository.RoleRepository;
import com.example.mirante.security.repository.UserRepository;
import com.example.mirante.security.service.JwtService;
import com.example.mirante.security.service.MiranteUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
public class AuthorizationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MiranteUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final Optional<User> user = userRepository.findByUsername(userDetails.getUsername());

        final Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);

        final String jwt = jwtTokenUtil.generateToken(userDetails, expirationDate);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user.get().getOperator(), expirationDate));
    }

    public User create(@Valid @RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There is already an user with this username");
        }

        return userRepository.save(user);
    }
}
