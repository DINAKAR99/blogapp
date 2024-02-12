package cgg.blogapp.blogapp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgg.blogapp.blogapp.entities.Comment;
import cgg.blogapp.blogapp.entities.CommentDTO;
import cgg.blogapp.blogapp.entities.Post;
import cgg.blogapp.blogapp.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogapp.repos.CommentRepo;
import cgg.blogapp.blogapp.repos.PostRepo;
import cgg.blogapp.blogapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        this.commentRepo.findById(
                commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

    }

}
