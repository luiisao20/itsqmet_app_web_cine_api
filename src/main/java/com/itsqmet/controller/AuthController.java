package com.itsqmet.controller;

import java.util.HashMap;
import java.util.Map;
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
import com.itsqmet.types.RequestBodyPassword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @GetMapping("validate")
  public ResponseEntity<Map<String, Object>> validateToken() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Token is valid");

    return ResponseEntity.ok(response);
  }

  @PostMapping("/change-password")
  public ResponseEntity<Map<String, Object>> getMethodName(@RequestBody RequestBodyPassword body ) {
    Map<String, Object> response = new HashMap<>();
    Optional<UserDTO> currentUser = userService.getUserByEmail(body.getEmail());

    if (currentUser.get() != null && userService.comparePasswords(body.getOldPassword(), body.getEmail())) {
      currentUser.get().setPassword(body.getNewPassword());
      userService.updateUser(currentUser.get().getUuid(), currentUser.get());
      response.put("message", "Password updated successfully");
      return ResponseEntity.ok(response);
    }
    response.put("message","Las contraseñas no coinciden");
    return ResponseEntity.ok(response);
  }

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
    authResponse.setUuid(currentUser.get().getUuid());

    return new ResponseEntity<>(authResponse, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO user) {
    userService.registerUser(user);
    return ResponseEntity.ok(Map.of("message", "ok"));
  }

}
