package com.geekster.ReceipeManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(name = "comment_user_id")
    private Long commentUserId;

    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    private LocalDateTime commentCreationTimeStamp;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;


}
