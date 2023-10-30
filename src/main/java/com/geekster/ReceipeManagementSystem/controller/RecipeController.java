package com.geekster.ReceipeManagementSystem.controller;

import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.service.RecipeService;
import com.geekster.ReceipeManagementSystem.service.UserService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    RecipeService recipeService;

    UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService){
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @GetMapping("/{recipeId")
    public Recipe getRecipe(@PathVariable Long recipeId){
        return recipeService.findRecipe(recipeId);
    }

    @GetMapping
    public Iterable<Recipe> getALlRecipe(){
        return recipeService.getRecipes();
    }

    @PostMapping
    public String createRecipe(@RequestBody Recipe recipe, @RequestParam String  email){
        return userService.createRecipe(recipe, email);
    }



    @PutMapping("/{recipeId}")
    public String updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe updatedRecipe, @RequestParam String email){
        User currenUser = userService.getUserByEmail(email);
        return recipeService.updateRecipe(recipeId, updatedRecipe, currenUser);
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId, @RequestParam String email){
        return userService.deleteRecipe(recipeId, email);
    }


}
