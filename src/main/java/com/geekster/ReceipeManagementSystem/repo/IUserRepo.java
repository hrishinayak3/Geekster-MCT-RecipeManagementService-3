package com.geekster.ReceipeManagementSystem.repo;

import com.geekster.ReceipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

}
