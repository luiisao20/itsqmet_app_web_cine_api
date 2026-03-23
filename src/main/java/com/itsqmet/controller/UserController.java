package com.itsqmet.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.UserDTO;
import com.itsqmet.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping()
  public List<UserDTO> getUsers() {
    return userService.showUsers();
  }

  @GetMapping("/{uuid}")
  public Optional<UserDTO> getUserByUuid(@PathVariable String uuid) {
    return userService.getUserById(uuid);
  }

  @PutMapping("/update/{uuid}")
  public ResponseEntity<Map<String, String>> updateUser(@PathVariable String uuid, @RequestBody UserDTO user) {
    userService.updateUser(uuid, user);
    return ResponseEntity.ok(Map.of("message", "OK"));
  }

  @DeleteMapping("/delete/{uuid}")
  public void deleteUser(@PathVariable String uuid) {
    userService.deleteUser(uuid);
  }
}
