package com.los_acantilados.carta.carta.auth.service;

import com.los_acantilados.carta.carta.auth.dto.UserDTO;
import com.los_acantilados.carta.carta.auth.entity.UserEntity;
import com.los_acantilados.carta.carta.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsCustomService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* @Autowired
    private EmailService emailService;
    */

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);

        if (userEntity == null){
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = this.userRepository.save(userEntity);
/*
        if (userEntity != null){
            emailService.sendWelcomeMailTo(userEntity.getUsername());
        }

 */
        return userEntity != null;

    }
}
