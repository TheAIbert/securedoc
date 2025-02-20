package com.thealbert.securedoc.service;

import com.thealbert.securedoc.entity.CredentialEntity;
import com.thealbert.securedoc.entity.RoleEntity;
import com.thealbert.securedoc.entity.UserEntity;
import com.thealbert.securedoc.enumeration.Authority;
import com.thealbert.securedoc.repository.CredentialRepository;
import com.thealbert.securedoc.repository.UserRepository;
import com.thealbert.securedoc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CredentialRepository credentialRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Test
    @DisplayName("Test find user by ID")
    public void getUserByUserIdTest() {

        // Arrange - Given
        var userEntity = new UserEntity();
        userEntity.setFirstName("John");
        userEntity.setId(1L);
        userEntity.setUserId("1");
        userEntity.setCreatedAt(LocalDateTime.of(2000, 1, 1, 1, 1,1));
        userEntity.setUpdatedAt(LocalDateTime.of(2000, 1, 1, 1, 1,1));
        userEntity.setLastLogin(LocalDateTime.of(2000, 1, 1, 1, 1,1));

        var roleEntity = new RoleEntity("USER", Authority.USER);
        userEntity.setRole(roleEntity);

        var credentialEntity = new CredentialEntity();
        credentialEntity.setUpdatedAt(LocalDateTime.of(2000, 1, 1, 1, 1,1));
        credentialEntity.setPassword("password");
        credentialEntity.setUserEntity(userEntity);

        when(userRepository.findUserByUserId("1")).thenReturn(Optional.of(userEntity));
        when(credentialRepository.getCredentialByUserEntityId(1L)).thenReturn(Optional.of(credentialEntity));

        // Act - When
        var userByUserId = userServiceImpl.getUserByUserId("1");

        // Assert - Then
        assertThat(userByUserId.getFirstName()).isEqualTo(userEntity.getFirstName());
        assertThat(userByUserId.getUserId()).isEqualTo("1");
    }
}
