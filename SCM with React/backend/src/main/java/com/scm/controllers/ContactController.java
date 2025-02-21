package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helpers.Helper;
import com.scm.helpers.RandomImageSelector;
import com.scm.services.ContactService;
import com.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    // Add a new contact
    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestBody ContactForm contactForm, Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        String pictureUrl = RandomImageSelector.getRandomImageUrl();

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(pictureUrl);

        contactService.save(contact);

        return new ResponseEntity<String>("Contact added successfully", HttpStatus.CREATED);
    }

    // Get contacts with pagination
    @GetMapping
    public ResponseEntity<Page<Contact>> getContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> contacts = contactService.getByUser(user, page, size, sortBy, direction);
        return ResponseEntity.ok(contacts);
    }

    // Search contacts
    @PostMapping("/search")
    public ResponseEntity<Page<Contact>> searchContacts(
            @RequestBody ContactSearchForm searchForm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication) {

        System.out.println("Field is " + searchForm.getField() + "\nValue is" + searchForm.getValue());
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> results = contactService.searchByName(searchForm.getValue(), size, page, sortBy, direction, user);
        return ResponseEntity.ok(results);
    }

    // Delete a contact
    @DeleteMapping("/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable("contactId") String contactId, Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        contactService.delete(contactId, user);
        return ResponseEntity.ok("Contact deleted successfully");
    }

    // Get contact details
    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> getContact(@PathVariable("contactId") String contactId,
            Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Contact contact = contactService.getById(contactId, user);
        return ResponseEntity.ok(contact);
    }

    // Update a contact
    @PutMapping("/{contactId}")
    public ResponseEntity<?> updateContact(@PathVariable("contactId") String contactId,
            @RequestBody ContactForm contactForm,
            Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Contact contact = contactService.getById(contactId, user);

        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        contactService.update(contact);

        return ResponseEntity.ok("Contact updated successfully");
    }
}