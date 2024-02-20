package cgg.blogapp.blogapp.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cgg.blogapp.blogapp.entities.PostDTO;
import cgg.blogapp.blogapp.entities.User;
import cgg.blogapp.blogapp.entities.UserDTO;
import cgg.blogapp.blogapp.services.FileService;
import cgg.blogapp.blogapp.services.PostService;
import cgg.blogapp.blogapp.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "din_scheme")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private FileService fileService;

    private String path;

    @Autowired
    public PostService postService;
    @Autowired
    public UserService userService;
    @Autowired
    public ModelMapper mapper;

    @PostMapping("/")
    public ResponseEntity<PostDTO> createPost(
            @Valid @RequestBody PostDTO PostDTO) {
        PostDTO createdCategory = postService.createPost(PostDTO);
        return new ResponseEntity<PostDTO>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{postid}")
    public ResponseEntity<PostDTO> getPost(
            @PathVariable("postid") int postid) {

        return ResponseEntity.ok(postService.getPost(postid));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> allPosts = postService.getAllPosts();
        return new ResponseEntity<List<PostDTO>>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getAllPostsByUser(@PathVariable("userId") Integer userId) {

        UserDTO userById = userService.getUserById(userId);

        User map = mapper.map(userById, User.class);

        List<PostDTO> allPosts = postService.getAllPostsByUser(map);
        return new ResponseEntity<List<PostDTO>>(allPosts, HttpStatus.OK);
    }

    @DeleteMapping("/{postid}")
    public ResponseEntity<ProblemDetail> deletePost(
            @PathVariable("postid") int PostId) {

        postService.deletePost(PostId);
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.OK, "post deleted successfully"))
                .build();
    }

    // post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDTO PostDTO = this.postService.getPost(postId);
        String fileName = this.fileService.uploadImage(path, image);

        PostDTO.setImageName(fileName);
        PostDTO updatePost = this.postService.updatePost(PostDTO, postId);
        return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);

    }

    // search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords) {
        List<PostDTO> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);

    }

}
