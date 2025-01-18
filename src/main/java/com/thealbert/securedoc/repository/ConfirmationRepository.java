package com.thealbert.securedoc.repository;

import com.thealbert.securedoc.entity.ConfirmationEntity;
import com.thealbert.securedoc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, Long> {

    Optional<ConfirmationEntity> findByKey(String key);
    Optional<ConfirmationEntity> findByUserEntity(UserEntity userEntity);
}
