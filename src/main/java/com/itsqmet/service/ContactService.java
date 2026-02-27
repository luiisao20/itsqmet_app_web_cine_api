package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.ContactDTO;
import com.itsqmet.entity.Contact;
import com.itsqmet.repository.ContactRepository;

@Service
public class ContactService {
  @Autowired
  private ContactRepository contactRepository;

  private ContactDTO mapToDTO(Contact contact) {
    ContactDTO dto = new ContactDTO();

    dto.setId(contact.getId());
    dto.setName(contact.getName());
    dto.setEmail(contact.getEmail());
    dto.setMessage(contact.getMessage());
    dto.setCreatedAt(contact.getCreatedAt());

    return dto;
  }

  private Contact mapToEntity(ContactDTO dto) {
    Contact contact = new Contact();

    contact.setId(dto.getId());
    contact.setName(dto.getName());
    contact.setEmail(dto.getEmail());
    contact.setMessage(dto.getMessage());
    contact.setCreatedAt(dto.getCreatedAt());

    return contact;
  }

  public List<ContactDTO> getContacts() {
    List<Contact> contacts = contactRepository.findAll();

    return contacts.stream().map(c -> mapToDTO(c)).collect(Collectors.toList());
  }

  public Optional<ContactDTO> findById(Long id) {
    return contactRepository.findById(id).map(c -> mapToDTO(c));
  }

  public ContactDTO save(ContactDTO contact) {
    return mapToDTO(contactRepository.save(mapToEntity(contact)));
  }

  public void deleteContact(Long id) {
    contactRepository.deleteById(id);
  }
}
