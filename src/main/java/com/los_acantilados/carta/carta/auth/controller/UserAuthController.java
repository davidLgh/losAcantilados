package com.los_acantilados.carta.carta.auth.controller;

import com.los_acantilados.carta.carta.auth.dto.AuthenticationRequest;
import com.los_acantilados.carta.carta.auth.dto.AuthenticationResponse;
import com.los_acantilados.carta.carta.auth.dto.UserDTO;
import com.los_acantilados.carta.carta.auth.repository.UserRepository;
import com.los_acantilados.carta.carta.auth.service.JwtUtils;
import com.los_acantilados.carta.carta.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private UserDetailsCustomService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    @Autowired
    public UserAuthController(
            UserDetailsCustomService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtUtils jwtTokenUtil){
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws InterruptedException, ExecutionException {
        this.userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponse> singUp(@RequestBody UserDTO user) throws Exception, InterruptedException, ExecutionException {

        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception, InterruptedException, ExecutionException {

        UserDetails userDetails;

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
