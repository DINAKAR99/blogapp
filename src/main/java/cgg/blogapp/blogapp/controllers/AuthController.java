package cgg.blogapp.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cgg.blogapp.blogapp.RefreshToken.RTRequest;
import cgg.blogapp.blogapp.RefreshToken.RefreshToken;
import cgg.blogapp.blogapp.RefreshToken.RefreshTokenRepo;
import cgg.blogapp.blogapp.RefreshToken.RefreshTokenService;
import cgg.blogapp.blogapp.jwt.JwtRequest;
import cgg.blogapp.blogapp.jwt.JwtResponse;
import cgg.blogapp.blogapp.jwt.JwtService;

@RestController
public class AuthController {

    @Autowired
    JwtService service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    RefreshTokenRepo refreshTokenRepo;

    @PostMapping("/authorize")
    public JwtResponse authbyNameAndPass(@RequestBody JwtRequest req) {

        String jwtToken = "0";
        boolean doAuthenticate = doAuthenticate(req.getUsername(), req.getPassword());

        if (doAuthenticate) {

            System.out.println(" authenticatted for genisis token generation ");
            jwtToken = service.generateJWTToken(req.getUsername());
            RefreshToken refreshtoken = refreshTokenService.createToken(req.getUsername());

            return JwtResponse.builder().jwt_token(jwtToken).refresh_token(refreshtoken.getRtoken()).build();

        } else {

            throw new UsernameNotFoundException("user not founduuu");
        }

    }

    @PostMapping("/recharge")
    public JwtResponse recharge(@RequestBody RTRequest req) {

        return

        refreshTokenRepo.findByRtoken(req.token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getU1)
                .map(u1 -> {
                    String jwttoken = service.generateJWTToken(u1.getUsername());
                    return JwtResponse.builder().jwt_token(jwttoken).refresh_token(req.getToken()).build();

                }).orElseThrow(() -> new RuntimeException("refresh token is not in the db"));
    }

    private boolean doAuthenticate(String string, String string2) {

        System.out.println(string + "------------");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                string, string2);

        try {

            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("invalid username or passowrd");
        }

        return true;

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(BadCredentialsException b) {

        return b.getMessage();

    }

}
