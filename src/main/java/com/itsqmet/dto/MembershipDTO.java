package com.itsqmet.dto;

import com.itsqmet.types.CardType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDTO {
  private Long id;
  private CardType cardType;
  private Integer currentVisits;
  private Integer topVisits;
  private Integer minVisits;
  private Float foodDiscount;
  private String generalDiscounts;
  private UserDTO userDTO;
}
