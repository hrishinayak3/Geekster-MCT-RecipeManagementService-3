package com.geekster.ReceipeManagementSystem.controller;


import com.geekster.ReceipeManagementSystem.model.Comment;
import com.geekster.ReceipeManagementSystem.model.User;
import com.geekster.ReceipeManagementSystem.service.CommentService;
import com.geekster.ReceipeManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comments")
public class CommentController {

    CommentService commentService;
    UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId){
        return commentService.findComment(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody Comment updateComment, @RequestParam String email){
        User curentUser = userService.getUserByEmail(email);
        return commentService.updateComment(updateComment, commentId, curentUser);

    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestParam String email){
        return userService.deleteComment(commentId, email);
    }


}
