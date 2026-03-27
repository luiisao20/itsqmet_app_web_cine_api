package com.itsqmet.views;

import org.springframework.beans.factory.annotation.Value;

public interface UserMembership {
  @Value("#{target.uuid}")
  String getUuid();

  @Value("#{target.nombre}")
  String getName();

  @Value("#{target.email}")
  String getEmail();

  @Value("#{target.telefono}")
  String getCellphone();

  @Value("#{target.tipo_tarjeta}")
  String getCardType();

  @Value("#{target.visitas_actuales}")
  Integer getCurrentVisits();

  @Value("#{target.visitas_minimas}")
  Integer getMinVisits();

  @Value("#{target.descuento_comida}")
  Float getFoodDiscount();
}
