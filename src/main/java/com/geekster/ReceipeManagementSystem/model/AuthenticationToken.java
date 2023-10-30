package com.geekster.ReceipeManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String tokenValue;

    private LocalDateTime tokenCreationTime;

    @OneToOne
    User user;


    public AuthenticationToken(User user){
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime= LocalDateTime.now();

    }

}
