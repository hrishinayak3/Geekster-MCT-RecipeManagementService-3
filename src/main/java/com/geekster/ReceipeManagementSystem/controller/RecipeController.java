package com.geekster.ReceipeManagementSystem.controller;


import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.service.RecipeService;
import com.geekster.ReceipeManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @GetMapping("/{recipeId}")
    public Recipe getRecipe(@PathVariable Long recipeId) {
        return recipeService.findRecipe(recipeId);
    }

    @GetMapping
    public Iterable<Recipe>getAllRecipes()
    {
        return recipeService.getRecipes();
    }

    @PostMapping
    public String createRecipe(@RequestBody Recipe recipe, @RequestParam String email) {
        return userService.createRecipe(recipe, email);
    }


    @PutMapping("/{recipeId}")
    public String updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe updatedRecipe, @RequestParam String email) {
        User currentUser = userService.getUserByEmail(email);
        return recipeService.updateRecipe(recipeId, updatedRecipe, currentUser);
    }


    @DeleteMapping("/{recipeId}")
    public String removeRecipe(@PathVariable Long recipeId, @RequestParam String email) {
        return userService.removeRecipe(recipeId, email);
    }
}
