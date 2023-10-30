package com.geekster.ReceipeManagementSystem.service;


import com.geekster.ReceipeManagementSystem.model.AuthenticationToken;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.repo.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationRepository authenticationRepo;

    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getEmail();

        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(AuthenticationToken authToken)
    {
        authenticationRepo.save(authToken);
    }

    public AuthenticationToken findFirstByUser(User user) {
        return authenticationRepo.findFirstByUser(user);
    }

    public void removeToken(AuthenticationToken token) {
        authenticationRepo.delete(token);
    }
}

