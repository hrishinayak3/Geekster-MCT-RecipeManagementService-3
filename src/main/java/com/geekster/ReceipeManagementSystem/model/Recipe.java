package com.geekster.ReceipeManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "instructions")
    private String instructions;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime recipeAddedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;


}
