package com.scm;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repsitories.ContactRepo;
import com.scm.services.impl.ContactServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContactServiceTests {

    @Mock
    private ContactRepo contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    private Contact contact;
    private User user;

    @BeforeEach
    void setUp() {
        // User details
        user = User.builder()
                .userId(1L)
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("7376207791")
                .password("helloMoto")
                .build();

        // Contact details
        contact = Contact.builder()
                .user(user)
                .id("1")
                .name("Alice")
                .email("Alice@example.com")
                .address("123 Main St")
                .phoneNumber("1234567890")
                .description("Alice's description")
                .user(user)
                .favorite(true)
                .build();
    }

    @Test
    void testSaveContact() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact savedContact = contactService.save(contact);

        assertNotNull(savedContact);
        assertEquals("Alice", savedContact.getName());
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    void testUpdateContact() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.update(contact);

        assertNotNull(updatedContact);
        assertEquals("Alice", updatedContact.getName());
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    void testGetAllContacts() {
        Contact contact2 = new Contact();
        contact2.setUser(user);
        contact2.setId("2");
        contact2.setName("Bob");
        contact2.setEmail("bob@example.com");
        contact2.setPhoneNumber("9876543210");
        contact2.setAddress("456 Elm St");
        contact2.setDescription("Bob's description");
        contact2.setFavorite(false);
        List<Contact> contacts = Arrays.asList(contact, contact2);
        when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> retrievedContacts = contactService.getAll();

        assertEquals(2, retrievedContacts.size());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    void testGetContactById() {
        when(contactRepository.findById("1")).thenReturn(Optional.of(contact));

        Contact foundContact = contactService.getById("1");

        assertNotNull(foundContact);
        assertEquals("Alice", foundContact.getName());
        verify(contactRepository, times(1)).findById("1");
    }

    @Test
    void delete_ShouldDeleteContact_WhenContactExists() {
        // Arrange
        when(contactRepository.findByIdAndUser("1", user)).thenReturn(contact);

        // Act
        contactService.delete("1", user);

        // Assert
        verify(contactRepository, times(1)).delete(contact);
    }

    @Test
    void delete_ShouldThrowException_WhenContactDoesNotExist() {
        // Arrange
        when(contactRepository.findByIdAndUser("999", user)).thenReturn(null);

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                contactService.delete("999", user));

        assertEquals("Contact not found with given id 999", exception.getMessage());
        verify(contactRepository, never()).delete(any());
    }

    @Test
    void testGetByUserId() {
        Contact contact2 = new Contact();
        contact2.setUser(user);
        contact2.setId("2");
        contact2.setName("Bob");
        contact2.setEmail("bob@example.com");
        contact2.setPhoneNumber("9876543210");
        contact2.setAddress("456 Elm St");
        contact2.setDescription("Bob's description");
        contact2.setFavorite(false);
        List<Contact> contacts = Arrays.asList(contact, contact2);
        when(contactRepository.findByUserId("1")).thenReturn(contacts);

        List<Contact> userContacts = contactService.getByUserId("1");

        assertEquals(2, userContacts.size());
        verify(contactRepository, times(1)).findByUserId("1");
    }

    @Test
    void testGetByUserWithPagination() {
        Page<Contact> page = new PageImpl<>(List.of(contact));
        when(contactRepository.findByUser(eq(user), any(Pageable.class))).thenReturn(page);

        Page<Contact> resultPage = contactService.getByUser(user, 0, 5, "name", "ASC");

        assertEquals(1, resultPage.getContent().size());
        verify(contactRepository, times(1)).findByUser(eq(user), any(Pageable.class));
    }
}