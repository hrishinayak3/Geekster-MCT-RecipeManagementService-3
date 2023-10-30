package com.geekster.ReceipeManagementSystem.service;

import com.geekster.ReceipeManagementSystem.model.AuthenticationToken;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    public boolean authenticate(String email, String authTokenValue){
        AuthenticationToken authToken = iAuthenticationRepo.findFirstByTokenValue(authTokenValue);

        if( authToken == null){
            return false;
        }

        String tokenEmailConnected = authToken.getUser().getEmail();
        return tokenEmailConnected.equals(email);
    }

    public void saveAuthToken(AuthenticationToken authToken){
        iAuthenticationRepo.save(authToken);
    }

    public void removeToken(AuthenticationToken authToken){
        iAuthenticationRepo.delete(authToken);
    }

    public AuthenticationToken findFirstByUser(User user){
        return iAuthenticationRepo.findFirstByUser(user);
    }
}
