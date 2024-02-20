package cgg.blogapp.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgg.blogapp.blogapp.entities.Post;
import cgg.blogapp.blogapp.entities.PostDTO;
import cgg.blogapp.blogapp.entities.User;
import cgg.blogapp.blogapp.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogapp.repos.PostRepo;
import cgg.blogapp.blogapp.services.PostService;

@Service
public class PostServImpl implements PostService {

    @Autowired
    private PostRepo postRepository;

    @Autowired
    ModelMapper m1;

    @Override
    public PostDTO createPost(PostDTO PostDTO) {
        Post postDtoToPost = postDtoToPost(PostDTO);
        Post save = postRepository.save(postDtoToPost);

        return postToPostDto(save);

    }

    // @Override
    // public PostDTO updatePost(int PostId, PostDTO PostDTO) {
    // Post existingPost = PostRepository
    // .findById(PostId)
    // .orElseThrow(() -> new ResourceNotFoundException("Post", "id", PostId));
    // existingPost.setPosttitle(PostDTO.getPosttitle());
    // existingPost.setPostcontent(
    // PostDTO.getPostcontent());
    // Post updatedPost = PostRepository.save(existingPost);
    // return postToPostDto(updatedPost);
    // }

    @Override
    public void deletePost(int postId) {

        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        postRepository.deleteById(postId);

    }

    @Override
    public PostDTO getPost(int PostId) {
        Post existingPost = postRepository
                .findById(PostId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", PostId));
        return postToPostDto(existingPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> allCategories = postRepository.findAll();
        List<PostDTO> PostDTOs = allCategories
                .stream()
                .map(Post -> postToPostDto(Post))
                .collect(Collectors.toList());
        return PostDTOs;
    }

    @Override
    public List<PostDTO> getAllPostsByUser(User user) {
        List<Post> allCategories = postRepository.findByUser(user);
        List<PostDTO> PostDTOs = allCategories
                .stream()
                .map(Post -> postToPostDto(Post))
                .collect(Collectors.toList());
        return PostDTOs;
    }

    public Post postDtoToPost(PostDTO PostDTO) {
        return m1.map(PostDTO, Post.class);
    }

    public PostDTO postToPostDto(Post Post) {
        return m1.map(Post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO PostDTO, Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        post.setPosttitle(PostDTO.getPosttitle());
        post.setPostcontent(PostDTO.getPostcontent());
        post.setImageName(PostDTO.getImageName());
        Post updatePost = this.postRepository.save(post);
        return this.m1.map(updatePost, PostDTO.class);
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = this.postRepository.findByPosttitleContaining(keyword);
        List<PostDTO> PostDTOs = posts.stream().map(post -> this.m1.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return PostDTOs;
    }

}
