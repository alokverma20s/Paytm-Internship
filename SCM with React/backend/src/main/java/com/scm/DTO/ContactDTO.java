package com.scm.DTO;

import com.scm.entities.Contact;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedInLink;
    private String userId; // Only store user ID, not full object

    public static ContactDTO fromEntity(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .address(contact.getAddress())
                .picture(contact.getPicture())
                .description(contact.getDescription())
                .favorite(contact.isFavorite())
                .websiteLink(contact.getWebsiteLink())
                .linkedInLink(contact.getLinkedInLink())
                .userId(contact.getUser().getEmail()) // Avoid lazy loading issues
                .build();
    }

    public static Contact toEntity(ContactDTO contactDTO) {
        return Contact.builder()
                .id(contactDTO.getId())
                .name(contactDTO.getName())
                .email(contactDTO.getEmail())
                .phoneNumber(contactDTO.getPhoneNumber())
                .address(contactDTO.getAddress())
                .picture(contactDTO.getPicture())
                .description(contactDTO.getDescription())
                .favorite(contactDTO.isFavorite())
                .websiteLink(contactDTO.getWebsiteLink())
                .linkedInLink(contactDTO.getLinkedInLink())
                .build();
    }
}
