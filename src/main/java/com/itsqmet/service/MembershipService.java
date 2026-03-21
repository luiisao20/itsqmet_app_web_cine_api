package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.MembershipDTO;
import com.itsqmet.entity.Membership;
import com.itsqmet.repository.MembershipRepository;

@Service
public class MembershipService {
  @Autowired
  private MembershipRepository membershipRepository;

  @Autowired
  private UserService userService;

  private Membership mapToEntity(MembershipDTO dto) {
    Membership membership = new Membership();

    membership.setId(dto.getId());
    membership.setCardType(dto.getCardType());
    membership.setCurrentVisits(dto.getCurrentVisits());
    membership.setTopVisits(dto.getTopVisits());
    membership.setMinVisits(dto.getMinVisits());
    membership.setFoodDiscount(dto.getFoodDiscount());
    membership.setGeneralDiscounts(dto.getGeneralDiscounts());

    return membership;
  }

  private MembershipDTO mapToDTO(Membership membership) {
    MembershipDTO dto = new MembershipDTO();

    dto.setId(membership.getId());
    dto.setCardType(membership.getCardType());
    dto.setCurrentVisits(membership.getCurrentVisits());
    dto.setTopVisits(membership.getTopVisits());
    dto.setMinVisits(membership.getMinVisits());
    dto.setFoodDiscount(membership.getFoodDiscount());
    dto.setGeneralDiscounts(membership.getGeneralDiscounts());
    dto.setUserDTO(userService.mapToDTO(membership.getUser()));

    return dto;
  }

  public MembershipDTO save(MembershipDTO membership) {
    return mapToDTO(membershipRepository.save(mapToEntity(membership)));
  }

  public List<MembershipDTO> getAll() {
    return membershipRepository.findAll().stream()
        .map(m -> mapToDTO(m))
        .collect(Collectors.toList());
  }

  public Optional<MembershipDTO> getMembership(Long id) {
    return membershipRepository.findById(id)
        .map(m -> mapToDTO(m));
  }

  public void delete(Long id) {
    membershipRepository.deleteById(id);
  }

  public Optional<MembershipDTO> getMembershipByUser(String id) {
    return membershipRepository.findByUserUuid(id)
        .map(m -> mapToDTO(m));
  }
}
