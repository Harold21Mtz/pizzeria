package com.platzi.pizzeria.repository;

import com.platzi.pizzeria.entity.UserEntity;
import com.platzi.pizzeria.entity.projection.InfoBasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUserName(String username);
    @Query(value = "SELECT u.user_administrator AS administrator, u.profile_image AS profileImage, p.person_name AS personName, p.person_last_name AS personLastName " +
            "FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id WHERE u.user_id = :userId", nativeQuery = true)
    InfoBasicUser getInfoOfAUser(@Param("userId") UUID userId);
}