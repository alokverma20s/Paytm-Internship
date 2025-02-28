package com.scm.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {
  private Long userId;
  private String name;
  private String email;
  private String about;
  private String profilePic;
  private String phoneNumber;
  private boolean enabled;
  private boolean emailVerified;
  private boolean phoneVerified;
  private Providers provider;
  private String providerUserId;

  public static User toEntity(UserDTO dto) {
    User user = new User();
    user.setUserId(dto.getUserId());
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setAbout(dto.getAbout());
    user.setProfilePic(dto.getProfilePic());
    user.setPhoneNumber(dto.getPhoneNumber());
    user.setEnabled(dto.isEnabled());
    user.setEmailVerified(dto.isEmailVerified());
    user.setPhoneVerified(dto.isPhoneVerified());
    user.setProvider(dto.getProvider());
    user.setProviderUserId(dto.getProviderUserId());
    return user;
  }
  public static UserDTO fromEntity(User dto) {
    UserDTO user = new UserDTO();
    user.setUserId(dto.getUserId());
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setAbout(dto.getAbout());
    user.setProfilePic(dto.getProfilePic());
    user.setPhoneNumber(dto.getPhoneNumber());
    user.setEnabled(dto.isEnabled());
    user.setEmailVerified(dto.isEmailVerified());
    user.setPhoneVerified(dto.isPhoneVerified());
    user.setProvider(dto.getProvider());
    user.setProviderUserId(dto.getProviderUserId());
    return user;
  }
}
