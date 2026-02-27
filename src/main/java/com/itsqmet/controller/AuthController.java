package com.itsqmet.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.UserDTO;
import com.itsqmet.jwt.JwtProvider;
import com.itsqmet.response.AuthResponse;
import com.itsqmet.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> loginUser(@RequestBody UserDTO user) {
    Authentication authentication = userService.loginAction(user.getEmail(), user.getPassword());
    Optional<UserDTO> currentUser = userService.getUserByEmail(user.getEmail());

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse();

    authResponse.setMessage("Loging exitoso");
    authResponse.setJwt(jwt);
    authResponse.setStatus(true);
    authResponse.setEmail(currentUser.get().getEmail());
    authResponse.setRole(currentUser.get().getRole().name());

    return new ResponseEntity<>(authResponse, HttpStatus.OK);
  }

}
