package cgg.blogapp.blogapp.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private int postid;

    @NotBlank
    @Size(min = 4, message = "postname should be 4 letter minimum ")
    private String posttitle;

    @NotBlank
    @Size(min = 4, message = " post desc should be 4 letter minimum ")
    private String postcontent;

    private String imageName;

    public LocalDateTime addedDate;
    public Category category;
    public User user;

    private Set<Comment> comments = new HashSet<>();

    public PostDTO() {
        this.addedDate = LocalDateTime.now();
        System.out.println("in default const........----");
        System.out.println(addedDate);
    }

}
