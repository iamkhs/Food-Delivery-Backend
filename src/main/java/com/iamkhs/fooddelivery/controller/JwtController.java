package com.iamkhs.fooddelivery.controller;

import com.iamkhs.fooddelivery.dto.UserDto;
import com.iamkhs.fooddelivery.request.JwtRequest;
import com.iamkhs.fooddelivery.response.JwtResponse;
import com.iamkhs.fooddelivery.jwtconfig.JwtUtil;
import com.iamkhs.fooddelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food-app/api/auth/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JwtController {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
        System.out.println(jwtRequest);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.username());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.username(), jwtRequest.password()));
            String generatedToken = jwtUtil.generateToken(userDetails);
            UserDto userDto = this.userService.getUser(jwtRequest.username());

            JwtResponse jwtResponse = new JwtResponse(generatedToken, userDto);

            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

        }catch (RuntimeException e){
            throw new BadCredentialsException("Incorrect Email or Password!!! " + e.getMessage());
        }
    }


}
