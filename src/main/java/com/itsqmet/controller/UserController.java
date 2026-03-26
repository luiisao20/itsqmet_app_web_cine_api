package com.itsqmet.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
  public Page<UserDTO> getUsers(@PageableDefault(size = 10) Pageable pageable) {
    return userService.showUsers(pageable);
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
