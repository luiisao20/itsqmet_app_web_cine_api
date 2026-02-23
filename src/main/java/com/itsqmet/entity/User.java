package com.itsqmet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.itsqmet.roles.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String uuid;
  
  @NotBlank
  private String name;

  @NotBlank
  @Column(unique = true)
  private String email;

  @Enumerated(EnumType.STRING)
  private Role role;

  private String cellphone;

  private String password;
}
