package cgg.blogapp.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {
    private int id;
    private String content;
    @JsonIgnore
    private Post post;
}
