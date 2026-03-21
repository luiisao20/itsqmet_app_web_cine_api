package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.MembershipDTO;
import com.itsqmet.service.MembershipService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/memberships")
public class MembershipController {
  @Autowired
  private MembershipService membershipService;

  @GetMapping
  public List<MembershipDTO> getAll() {
    return membershipService.getAll();
  }

  @GetMapping("/{id}")
  public Optional<MembershipDTO> getItem(@PathVariable Long id) {
    return membershipService.getMembership(id);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    membershipService.delete(id);
  }

  @GetMapping("/user/{id}")
  public Optional<MembershipDTO> getByUser(@PathVariable String id) {
    System.out.println(id);
    return membershipService.getMembershipByUser(id);
  }

}
