package cgg.blogapp.blogapp.services;

import org.springframework.stereotype.Service;

import cgg.blogapp.blogapp.entities.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDto, Integer postId);

    void deleteComment(Integer commentId);
}