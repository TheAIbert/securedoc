package com.thealbert.securedoc.service;

import com.thealbert.securedoc.dto.User;
import com.thealbert.securedoc.entity.CredentialEntity;
import com.thealbert.securedoc.entity.RoleEntity;
import com.thealbert.securedoc.enumeration.LoginType;

public interface UserService {

    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
    void verifyAccountKey(String key);
    void updateLoginAttempt(String email, LoginType loginType);
    User getUserByUserId(String userId);
    User getUserByEmail(String email);
    CredentialEntity getUserCredentialById(Long id);
}
