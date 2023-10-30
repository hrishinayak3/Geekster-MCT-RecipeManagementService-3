package com.geekster.ReceipeManagementSystem.repo;

import com.geekster.ReceipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String newEmail);
}

