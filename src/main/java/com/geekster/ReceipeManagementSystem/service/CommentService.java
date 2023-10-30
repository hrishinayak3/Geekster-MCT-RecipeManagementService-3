package com.geekster.ReceipeManagementSystem.service;

import com.geekster.ReceipeManagementSystem.model.Comment;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    ICommentRepo iCommentRepo;

    public String addcomment(Comment comment){
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        iCommentRepo.save(comment);
        return "Comment added!";
    }

    public List<Comment> getCommentForRecipe(Long recipeId){
        return iCommentRepo.findByCommenterUserId(recipeId);

    }

    public List<Comment> getAllComment(){
        return iCommentRepo.findAll();
    }

    public Comment updateComment(Comment updateComment, Long commentId, User currenUser){
        Comment existingComment = iCommentRepo.findById(commentId).orElse(null);


        if( existingComment != null) {
            if (existingComment.getUser().equals(currenUser)) {
                existingComment.setUser(updateComment.getUser());
                return iCommentRepo.save(existingComment);
            } else {
                throw new IllegalStateException("You cannot make changes for this user");
            }
        }
        return null;

    }

    public void deleteComment(Comment comment){
        iCommentRepo.delete(comment);
    }


    public Comment findComment(Long commentId) {
        return  iCommentRepo.findById(commentId).orElse(null);
    }
}
