package com.thealbert.securedoc.service;

import com.thealbert.securedoc.entity.RoleEntity;

public interface UserService {

    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
    void verifyAccountKey(String key);
}
