package com.scm.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helpers.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

  @Mock
  private ContactService contactService;

  @Mock
  private UserService userService;

  @Mock
  private Authentication authentication;

  @InjectMocks
  private ContactController contactController;

  private User user;
  private Contact contact;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setUserId(1L);
    user.setEmail("test@example.com");

    contact = new Contact();
    contact.setId("contact123");
    contact.setName("John Doe");
    contact.setEmail("john@example.com");
    contact.setPhoneNumber("1234567890");
    contact.setUser(user);
  }

  @Test
  void testAddContact_Success() {
    ContactForm contactForm = new ContactForm();
    contactForm.setName("John Doe");
    contactForm.setEmail("john@example.com");
    contactForm.setPhoneNumber("1234567890");

    when(Helper.getEmailOfLoggedInUser(authentication)).thenReturn("test@example.com");
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);
    when(contactService.save(any(Contact.class))).thenReturn(contact);

    ResponseEntity<?> response = contactController.addContact(contactForm, authentication);

    assertNotNull(response);
    assertEquals(201, response.getStatusCode().value());
    assertEquals("Contact added successfully", response.getBody());
    verify(contactService, times(1)).save(any(Contact.class));
  }

  @Test
  void testGetContacts_Success() {
    Page<Contact> contacts = new PageImpl<>(List.of(contact));

    when(Helper.getEmailOfLoggedInUser(authentication)).thenReturn("test@example.com");
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);
    when(contactService.getByUser(user, 0, 10, "name", "asc")).thenReturn(contacts);

    ResponseEntity<Page<Contact>> response = contactController.getContacts(0, 10, "name", "asc", authentication);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());

    // Check before calling getBody()
    assertTrue(response.hasBody(), "Response should have a body");
    assertNotNull(response.getBody(), "Response body should not be null");

    assertEquals(1, response.getBody().getTotalElements());
    verify(contactService, times(1)).getByUser(user, 0, 10, "name", "asc");
  }

  @Test
  void testSearchContacts_Success() {
    ContactSearchForm searchForm = new ContactSearchForm();
    searchForm.setField("name");
    searchForm.setValue("John");

    Page<Contact> contacts = new PageImpl<>(List.of(contact));

    when(Helper.getEmailOfLoggedInUser(authentication)).thenReturn("test@example.com");
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);
    when(contactService.searchByName("John", 10, 0, "name", "asc", user)).thenReturn(contacts);

    ResponseEntity<Page<Contact>> response = contactController.searchContacts(searchForm, 0, 10, "name", "asc",
        authentication);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    assertEquals(1, response.getBody().getTotalElements());
    verify(contactService, times(1)).searchByName("John", 10, 0, "name", "asc", user);
  }

  @Test
  void testDeleteContact_Success() {
    when(Helper.getEmailOfLoggedInUser(authentication)).thenReturn("test@example.com");
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);
    doNothing().when(contactService).delete("contact123", user);

    ResponseEntity<?> response = contactController.deleteContact("contact123", authentication);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    assertEquals("Contact deleted successfully", response.getBody());
    verify(contactService, times(1)).delete("contact123", user);
  }

  @Test
  void testGetContact_Success() {
    when(Helper.getEmailOfLoggedInUser(authentication)).thenReturn("test@example.com");
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);
    when(contactService.getById("contact123", user)).thenReturn(contact);

    ResponseEntity<Contact> response = contactController.getContact("contact123", authentication);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    assertEquals("John Doe", response.getBody().getName());
    verify(contactService, times(1)).getById("contact123", user);
  }

  @Test
  void testUpdateContact_Success() {
    ContactForm contactForm = new ContactForm();
    contactForm.setName("Updated Name");
    contactForm.setEmail("updated@example.com");
    contactForm.setPhoneNumber("9876543210");

    when(Helper.getEmailOfLoggedInUser(authentication)).thenReturn("test@example.com");
    when(userService.getUserByEmail("test@example.com")).thenReturn(user);
    when(contactService.getById("contact123", user)).thenReturn(contact);
    when(contactService.update(any(Contact.class))).thenReturn(contact);

    ResponseEntity<?> response = contactController.updateContact("contact123", contactForm, authentication);

    assertNotNull(response);
    assertEquals(200, response.getStatusCode().value());
    assertEquals("Contact updated successfully", response.getBody());
    verify(contactService, times(1)).update(any(Contact.class));
  }
}
