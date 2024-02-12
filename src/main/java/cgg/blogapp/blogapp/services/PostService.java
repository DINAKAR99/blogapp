package cgg.blogapp.blogapp.services;

import java.util.List;

import cgg.blogapp.blogapp.entities.PostDTO;

public interface PostService {
    public PostDTO createPost(PostDTO PostDTO);

    // public PostDTO updatePost(int PostId, PostDTO PostDTO);

    public void deletePost(int PostId);

    public PostDTO getPost(int PostId);

    public List<PostDTO> getAllPosts();

    PostDTO updatePost(PostDTO PostDTO, Integer postId);

    // search posts
    List<PostDTO> searchPosts(String keyword);
}
