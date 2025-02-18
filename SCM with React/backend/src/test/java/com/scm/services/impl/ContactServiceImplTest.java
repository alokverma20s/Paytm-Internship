package com.scm.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repsitories.ContactRepo;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactRepo contactRepo;

    @InjectMocks
    private ContactServiceImpl contactService;

    private Contact contact;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(123L);
        user.setEmail("user@example.com");

        contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setName("John Doe");
        contact.setEmail("john@example.com");
        contact.setPhoneNumber("1234567890");
        contact.setUser(user);
    }

    @Test
    void testSaveContact() {
        when(contactRepo.save(any(Contact.class))).thenReturn(contact);

        Contact savedContact = contactService.save(contact);

        assertNotNull(savedContact);
        assertEquals("John Doe", savedContact.getName());
        verify(contactRepo, times(1)).save(any(Contact.class));

    }

    @Test
    void testUpdateContact() {
        when(contactRepo.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.update(contact);

        assertNotNull(updatedContact);
        assertEquals("John Doe", updatedContact.getName());
        verify(contactRepo, times(1)).save(any(Contact.class));
    }

    @Test
    void testGetAllContacts() {
        List<Contact> contacts = Arrays.asList(contact);
        when(contactRepo.findAll()).thenReturn(contacts);

        List<Contact> result = contactService.getAll();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(contactRepo, times(1)).findAll();
    }

    @Test
    void testGetContactById_Success() {
        when(contactRepo.findById(contact.getId())).thenReturn(Optional.of(contact));

        Contact foundContact = contactService.getById(contact.getId());

        assertNotNull(foundContact);
        assertEquals("John Doe", foundContact.getName());
        verify(contactRepo, times(1)).findById(contact.getId());
    }

    @Test
    void testGetContactById_NotFound() {
        when(contactRepo.findById("invalid-id")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            contactService.getById("invalid-id");
        });

        assertTrue(exception.getMessage().contains("Contact not found"));
        verify(contactRepo, times(1)).findById("invalid-id");
    }

    @Test
    void testDeleteContact_Success() {
        when(contactRepo.findByIdAndUser(contact.getId(), user)).thenReturn(contact);
        doNothing().when(contactRepo).delete(contact);

        contactService.delete(contact.getId(), user);

        verify(contactRepo, times(1)).delete(contact);
    }

    @Test
    void testDeleteContact_NotFound() {
        when(contactRepo.findByIdAndUser("invalid-id", user)).thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            contactService.delete("invalid-id", user);
        });

        assertTrue(exception.getMessage().contains("Contact not found"));
        verify(contactRepo, never()).delete(any(Contact.class));
    }

    @Test
    void testGetContactsByUserId() {
        when(contactRepo.findByUserId(user.getUserId().toString())).thenReturn(List.of(contact));

        List<Contact> contacts = contactService.getByUserId(user.getUserId().toString());

        assertEquals(1, contacts.size());
        assertEquals("John Doe", contacts.get(0).getName());
        verify(contactRepo, times(1)).findByUserId(user.getUserId().toString());
    }

    @Test
    void testGetContactsByUserWithPagination() {
        PageRequest pageable = PageRequest.of(0, 5, Sort.by("name").ascending());
        Page<Contact> pagedContacts = new PageImpl<>(List.of(contact));

        when(contactRepo.findByUser(user, pageable)).thenReturn(pagedContacts);

        Page<Contact> result = contactService.getByUser(user, 0, 5, "name", "asc");

        assertEquals(1, result.getContent().size());
        assertEquals("John Doe", result.getContent().get(0).getName());
        verify(contactRepo, times(1)).findByUser(user, pageable);
    }

    @Test
    void testSearchByName() {
        PageRequest pageable = PageRequest.of(0, 5, Sort.by("name").ascending());
        Page<Contact> pagedContacts = new PageImpl<>(List.of(contact));

        when(contactRepo.findByUserAndNameContaining(user, "John", pageable)).thenReturn(pagedContacts);

        Page<Contact> result = contactService.searchByName("John", 5, 0, "name", "asc", user);

        assertEquals(1, result.getContent().size());
        assertEquals("John Doe", result.getContent().get(0).getName());
        verify(contactRepo, times(1)).findByUserAndNameContaining(user, "John", pageable);
    }

    @Test
    void testSearchByEmail() {
        PageRequest pageable = PageRequest.of(0, 5, Sort.by("email").ascending());
        Page<Contact> pagedContacts = new PageImpl<>(List.of(contact));

        when(contactRepo.findByUserAndEmailContaining(user, "john@example.com", pageable)).thenReturn(pagedContacts);

        Page<Contact> result = contactService.searchByEmail("john@example.com", 5, 0, "email", "asc", user);

        assertEquals(1, result.getContent().size());
        assertEquals("john@example.com", result.getContent().get(0).getEmail());
        verify(contactRepo, times(1)).findByUserAndEmailContaining(user, "john@example.com", pageable);
    }

    @Test
    void testSearchByPhoneNumber() {
        PageRequest pageable = PageRequest.of(0, 5, Sort.by("phoneNumber").ascending());
        Page<Contact> pagedContacts = new PageImpl<>(List.of(contact));

        when(contactRepo.findByUserAndPhoneNumberContaining(user, "123", pageable)).thenReturn(pagedContacts);

        Page<Contact> result = contactService.searchByPhoneNumber("123", 5, 0, "phoneNumber", "asc", user);

        assertEquals(1, result.getContent().size());
        assertEquals("1234567890", result.getContent().get(0).getPhoneNumber());
        verify(contactRepo, times(1)).findByUserAndPhoneNumberContaining(user, "123", pageable);
    }

}
