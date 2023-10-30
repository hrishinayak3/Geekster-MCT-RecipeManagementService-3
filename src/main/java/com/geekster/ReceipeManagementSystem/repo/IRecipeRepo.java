package com.geekster.ReceipeManagementSystem.repo;

import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRecipeRepo extends JpaRepository<Recipe, Long> {

    List<Recipe> findOwnerAndId(User owner, Long id);

}
