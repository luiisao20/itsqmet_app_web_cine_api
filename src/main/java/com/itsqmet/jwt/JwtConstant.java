package com.itsqmet.jwt;

import org.springframework.beans.factory.annotation.Value;

public class JwtConstant {
  @Value("${jwt.secret.key}")
  public static String SECRET_KEY;

  public static final String JWT_HEADER = "Authorization";
}
