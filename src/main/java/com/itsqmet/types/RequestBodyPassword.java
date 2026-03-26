package com.itsqmet.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyPassword {
  private String email;
  private String oldPassword;
  private String newPassword;
}
