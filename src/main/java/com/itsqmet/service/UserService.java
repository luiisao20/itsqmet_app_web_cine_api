package com.itsqmet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

  public User mapToEntity(UserDTO dto) {
    User user = new User();

    user.setUuid(dto.getUuid());
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setCellphone(dto.getCellphone());
    user.setPassword(dto.getPassword());
    user.setRole(dto.getRole());

    return user;
  }

  public UserDTO mapToDTO(User user) {
    UserDTO dto = new UserDTO();

    dto.setUuid(user.getUuid());
    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setCellphone(user.getCellphone());
    dto.setRole(user.getRole());
    dto.setPassword(user.getPassword());
    dto.setCardId(user.getBenefits_card() != null ? user.getBenefits_card().getId() : null);

    return dto;
  }

  public Page<UserDTO> showUsers(Pageable pageable) {

    return userRepository.findAll(pageable)
        .map(u -> mapToDTO(u));
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
    UserDTO oldUser = getUserById(uuid)
        .orElseThrow(() -> new RuntimeException("User not found"));

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
    userRepository.deleteById(uuid);
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

  public Boolean comparePasswords(String password, String email) {
    UserDetails userDetails = loadUserByUsername(email);
    return passwordEncoder.matches(password, userDetails.getPassword());
  }

  public Authentication loginAction(String email, String password) {
    UserDetails userDetails = loadUserByUsername(email);

    if (passwordEncoder.matches(password, userDetails.getPassword())) {
      return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    throw new RuntimeException("Error de autenticación");
  }
}
