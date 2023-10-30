package com.geekster.ReceipeManagementSystem.repo;

import com.geekster.ReceipeManagementSystem.model.AuthenticationToken;
import com.geekster.ReceipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);


}
