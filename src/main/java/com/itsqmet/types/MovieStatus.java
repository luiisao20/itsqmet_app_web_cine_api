package com.itsqmet.types;

public enum MovieStatus {
  EN_CINES("En cines"),
  PROXIMAMENTE("Proximamente"),
  ESTRENO("Estreno");

  private final String value;

  MovieStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static MovieStatus fromValue(String value) {
    for (MovieStatus status : MovieStatus.values()) {
      if (status.value.equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Estado desconocido: " + value);
  }

}
