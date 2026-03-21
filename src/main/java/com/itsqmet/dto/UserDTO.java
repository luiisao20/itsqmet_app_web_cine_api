package com.itsqmet.dto;

import com.itsqmet.roles.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private String uuid;

  private String name;

  private String email;

  private Role role;

  private String cellphone;

  private String password;

  private Long cardId;
}
