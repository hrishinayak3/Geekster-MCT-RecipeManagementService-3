package com.geekster.ReceipeManagementSystem.controller;


import com.geekster.ReceipeManagementSystem.dto.SignInInput;
import com.geekster.ReceipeManagementSystem.dto.SignUpOutput;
import com.geekster.ReceipeManagementSystem.model.Comment;
import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/SignUp")
    public SignUpOutput signUpUser(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("/SignIn")
    public String signInUser(@RequestBody SignInInput signInInput) {
        return userService.signInUser(signInInput);
    }

    @PostMapping("/SignOut")
    public String signOutUser(@RequestParam String email) {
        return userService.signOutUser(email);
    }

    @PostMapping("/{email}/Addcomment")
    public String addCommentToRecipe(@RequestParam Long recipeId, @RequestParam String commentText, @PathVariable String email) {
        User currentUser = userService.getUserByEmail(email);
        return userService.addCommentToRecipe(recipeId, commentText, currentUser);
    }

    @PostMapping("/{email}/CreateRecipe")
    public String createRecipe(@RequestBody Recipe recipe, @PathVariable String email) {
        return userService.createRecipe(recipe, email);
    }


    @DeleteMapping("/{email}/DeleteRecipe")
    public String removeRecipe(@RequestParam Long recipeId, @PathVariable String email) {
        return userService.deleteRecipe(recipeId, email);
    }



    @DeleteMapping("/{email}/RemoveComment")
    public String removeComment(@RequestParam Long commentId, @PathVariable String email) {
        return userService.deleteComment(commentId, email);
    }

    @GetMapping("/users/{email}/AllRecipesByUser")
    public ResponseEntity<List<Recipe>> getAllRecipesByUser(@PathVariable String email) {
        List<Recipe> recipes = userService.getAllRecipesByUser(email);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/users/{userId}/AllCommentsByUser")
    public ResponseEntity<List<Comment>> getAllCommentsByUser(@PathVariable Long userId) {
        List<Comment> comments = userService.getAllCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }
}
