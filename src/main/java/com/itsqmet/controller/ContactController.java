package com.itsqmet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.ContactDTO;
import com.itsqmet.service.ContactService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/contact")
public class ContactController {
  @Autowired
  private ContactService contactService;

  @GetMapping()
  public List<ContactDTO> getContact() {
    return contactService.getContacts();
  }

  @PostMapping("/save")
  public ContactDTO saveContactDTO(@RequestBody ContactDTO contact) {
    return contactService.save(contact);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteContact(@PathVariable Long id) {
    contactService.deleteContact(id);
  }

}
