package com.example.mirante.security.service;

import com.example.mirante.security.model.MiranteUserDetails;
import com.example.mirante.security.model.User;
import com.example.mirante.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MiranteUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public MiranteUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(MiranteUserDetails::new).get();
    }
}
