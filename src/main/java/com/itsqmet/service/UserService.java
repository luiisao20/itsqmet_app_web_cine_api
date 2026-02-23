package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.UserDTO;
import com.itsqmet.entity.User;
import com.itsqmet.repository.UserRepository;
import com.itsqmet.roles.Role;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private User mapToEntity(UserDTO dto) {
    User user = new User();

    user.setUuid(dto.getUuid());
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setRole(dto.getRole());
    user.setCellphone(dto.getCellphone());
    user.setPassword(dto.getPassword());

    return user;
  }

  private UserDTO mapToDTO(User user) {
    UserDTO dto = new UserDTO();

    dto.setUuid(user.getUuid());
    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setRole(user.getRole());
    dto.setCellphone(user.getCellphone());
    dto.setPassword(user.getPassword());

    return dto;
  }

  public List<UserDTO> showUsers() {
    List<User> users = userRepository.findAll();

    return users.stream()
        .map(u -> mapToDTO(u))
        .collect(Collectors.toList());
  }

  public Optional<UserDTO> getUserById(String uuid) {
    return userRepository.findById(uuid)
        .map(u -> mapToDTO(u));
  }

  public Optional<UserDTO> getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(u -> mapToDTO(u));
  }

  public UserDTO updateUser(String uuid, UserDTO user) {
    UserDTO oldUser = getUserById(uuid).orElseThrow(() -> new RuntimeException("User not found"));
    oldUser.setName(user.getName());
    oldUser.setEmail(user.getEmail());
    oldUser.setCellphone(user.getCellphone());
    oldUser.setRole(user.getRole());

    if (user.getUuid() != null && user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
      oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    return mapToDTO(userRepository.save(mapToEntity(oldUser)));
  }

  public UserDTO registerUser(UserDTO user) {
    String passwordEncoded = passwordEncoder.encode(user.getPassword());
    user.setPassword(passwordEncoded);
    user.setRole(Role.ROLE_USER);
    return mapToDTO(userRepository.save(mapToEntity(user)));
  }

  public void deleteUser(String uuid) {
    UserDTO user = getUserById(uuid).orElseThrow(() -> new RuntimeException("User not found"));
    userRepository.delete(mapToEntity(user));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    return org.springframework.security.core.userdetails.User
        .builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().name())
        .build();
  }
}
