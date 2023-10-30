package com.geekster.ReceipeManagementSystem.service;


import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.repo.IRecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecipeService {


    @Autowired
    IRecipeRepo iRecipeRepo;

    public void addRecipe(Recipe recipe){
        recipe.setRecipeAddedTimeStamp(LocalDateTime.now());
        iRecipeRepo.save(recipe);
    }

    public Recipe findRecipe(Long recipeId){
        return iRecipeRepo.findById(recipeId).orElse(null);
    }

    public String updateRecipe(Long recipeId, Recipe updateRecipe, User currentUser ){
        Recipe existingRecipe = iRecipeRepo.findById(recipeId).orElse(null);

        if(existingRecipe != null && existingRecipe.equals(currentUser)){
            existingRecipe.setName(updateRecipe.getName());
            existingRecipe.setIngredients(updateRecipe.getIngredients());
            existingRecipe.setInstructions(updateRecipe.getInstructions());

            iRecipeRepo.save(existingRecipe);

            return "Recipe Updated!!";

        }
        else{
            throw new IllegalStateException("You cannot make changes for this user");
        }
    }

    public String removeRecipe(Long recipeId, User user){
        Recipe recipe  = iRecipeRepo.findById(recipeId).orElse(null);
        if( recipe != null && recipe.equals(user)){
            iRecipeRepo.deleteById(recipeId);
            return "Recipe deleted succesfully";
        }
        else{
            return "Unauthorized User";
        }

    }


    public Iterable<Recipe> getRecipes() {
        return iRecipeRepo.findAll();
    }

    public Recipe getRecipeById(Long recipeId) {
        return iRecipeRepo.findById(recipeId).orElse(null);
    }
}
