package com.eventshub.repository;

import com.eventshub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long > {

    Optional <User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.dateDeleted is null and u.id = ?1")
    User findNotDeletedUserById(Long id);

    @Query("SELECT u FROM User u WHERE u.dateDeleted is null")
    List<User> findAllNotDeletedUsers();

}
