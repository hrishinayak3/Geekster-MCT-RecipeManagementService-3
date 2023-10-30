package com.geekster.ReceipeManagementSystem.service;


import com.geekster.ReceipeManagementSystem.dto.SignInInput;
import com.geekster.ReceipeManagementSystem.dto.SignUpOutput;
import com.geekster.ReceipeManagementSystem.model.AuthenticationToken;
import com.geekster.ReceipeManagementSystem.model.Comment;
import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.repo.UserRepository;
import com.geekster.ReceipeManagementSystem.service.Hashing.PasswordEncryptor;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CommentService commentService;

    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getEmail();

        if (newEmail == null) {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

        User existingUser = userRepo.findFirstByEmail(newEmail);

        if (existingUser != null) {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(user.getPassword());

            user.setPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        } catch (Exception e) {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }
    }


    public String signInUser(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if (signInEmail == null) {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        User existingUser = userRepo.findFirstByEmail(signInEmail);

        if (existingUser == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(signInInput.getPassword());
            if (existingUser.getPassword().equals(encryptedPassword)) {
                AuthenticationToken authToken = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                return "Token has been created successfully!"+authToken.getTokenValue();
            } else {
                signInStatusMessage = "Invalid credentials!";
                return signInStatusMessage;
            }
        } catch (Exception e) {
            signInStatusMessage = "Internal error ";
            return signInStatusMessage;
        }

    }


    public String signOutUser(String email) {

        User user = userRepo.findFirstByEmail(email);
        AuthenticationToken token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }

    public String addCommentToRecipe(Long recipeId, String commentText, User currentUser) {
        Recipe recipe = recipeService.getRecipeById(recipeId);

        if (recipe != null) {
            Comment comment = new Comment();
            comment.setText(commentText);
            comment.setUser(currentUser);

            recipe.getComments().add(comment);

            recipeService.createRecipe(recipe);

            return "Comment added successfully";
        } else {
            throw new EntityNotFoundException("Recipe not found");
        }
    }

    public User getUserByEmail(String email) {
        return userRepo.findFirstByEmail(email);
    }

    public List<Recipe> getAllRecipesByUser(String email) {
        User user = userRepo.findFirstByEmail(email);

        if (user != null) {
            return user.getRecipes();
        }
        return Collections.emptyList();
    }

    public List<Comment> getAllCommentsByUserId(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        List<Comment> comments = new ArrayList<>();

        if (user != null) {
            List<Recipe> userRecipes = user.getRecipes();

            for (Recipe recipe : userRecipes) {
                List<Comment> recipeComments = recipe.getComments();

                for (Comment comment : recipeComments) {
                    if (!comment.getUser().getId().equals(userId)) {
                        comments.add(comment);
                    }
                }
            }
        }

        return comments;
    }
    public String removeRecipe(Long recipeId, String email){
        User user = userRepo.findFirstByEmail(email);
        return recipeService.removeRecipe(recipeId,user);
    }



    public String createRecipe(Recipe recipe, String email) {
        User recipeOwner = userRepo.findFirstByEmail(email);
        recipe.setOwner(recipeOwner);
        recipeService.createRecipe(recipe);
        return "Recipe created successfully";
    }



    boolean authorizeCommentRemover(String email, Comment comment)
    {
        String commentOwnerEmail = comment.getUser().getEmail();
        String recipeOwnerEmail = comment.getRecipe().getOwner().getEmail();

        return recipeOwnerEmail.equals(email) || commentOwnerEmail.equals(email);
    }

    public String removeComment(Long commentId, String email){
        Comment comment = commentService.findComment(commentId);
        if (comment!= null){
            if(authorizeCommentRemover(email,comment))
            {
                commentService.removeComment(comment);
                return "comment deleted successfully";
            }
            else
            {
                return "Unauthorized deletion ";
            }
        }else{
            return "Invalid Comment";
        }
    }
}
