package com.geekster.ReceipeManagementSystem.service;

import com.geekster.ReceipeManagementSystem.model.Comment;
import com.geekster.ReceipeManagementSystem.model.Recipe;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.repo.IUserRepo;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    CommentService commentService;

    @Autowired
    RecipeService recipeService;


    public User getUserByEmail(String email) {
        return iUserRepo.findFirstByEmail(email);
    }

    boolean authorizeCommentRemover(String email, Comment comment)
    {
        String commentOwnerEmail = comment.getUser().getEmail();
        String recipeOwnerEmail = comment.getRecipe().getOwner().getEmail();

        return recipeOwnerEmail.equals(email) || commentOwnerEmail.equals(email);
    }

    public String deleteComment(Long commentId, String email) {
        Comment comment = commentService.findComment(commentId);
        if (comment!= null){
            if(authorizeCommentRemover(email,comment))
            {
                commentService.deleteComment(comment);
                return "comment deleted successfully";
            }
            else
            {
                return "Unauthorized User!";
            }
        }else{
            return "Invalid Comment";
        }
    }

    public String createRecipe(Recipe recipe,String email) {
        User recipeOwner = iUserRepo.findFirstByEmail(email);
        recipe.setOwner(recipeOwner);
        recipeService.addRecipe(recipe);
        return "Recipe created successfully!";
    }

    public String deleteRecipe(Long recipeId, String email) {
        User user = iUserRepo.findFirstByEmail(email);
        return recipeService.removeRecipe(recipeId,user);
    }
}
