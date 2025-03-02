package org.example.springbootproj2.repository;

import org.example.springbootproj2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
