package com.thealbert.securedoc.service.impl;

import com.thealbert.securedoc.entity.ConfirmationEntity;
import com.thealbert.securedoc.entity.CredentialEntity;
import com.thealbert.securedoc.entity.RoleEntity;
import com.thealbert.securedoc.entity.UserEntity;
import com.thealbert.securedoc.enumeration.Authority;
import com.thealbert.securedoc.enumeration.EventType;
import com.thealbert.securedoc.event.UserEvent;
import com.thealbert.securedoc.exception.ApiException;
import com.thealbert.securedoc.repository.ConfirmationRepository;
import com.thealbert.securedoc.repository.CredentialRepository;
import com.thealbert.securedoc.repository.RoleRepository;
import com.thealbert.securedoc.repository.UserRepository;
import com.thealbert.securedoc.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.thealbert.securedoc.utils.UserUtils.createUserEntity;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final ConfirmationRepository confirmationRepository;
    // private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save(createNewUser(firstName, lastName, email));
        var credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public RoleEntity getRoleName(String name) {
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow(() -> new ApiException("Role not found"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }


    @Override
    public void verifyAccountKey(String key) {
        var confirmationEntity = getUserConfirmation(key);
        var userEntity = getUserEntityByEmail(confirmationEntity.getUserEntity().getEmail());
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        confirmationRepository.delete(confirmationEntity);
    }

    private UserEntity getUserEntityByEmail(String email) {
        var userByEmail = userRepository.findByEmailIgnoreCase(email);
        return userByEmail.orElseThrow(() -> new ApiException("User not found"));
    }

    private ConfirmationEntity getUserConfirmation(String key) {
        return confirmationRepository.findByKey(key).orElseThrow(() -> new ApiException("Confirmation key not found"));
    }
}
