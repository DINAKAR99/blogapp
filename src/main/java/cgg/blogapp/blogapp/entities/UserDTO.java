package cgg.blogapp.blogapp.entities;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull
    public int id;
    @NotBlank
    public String name;
    @NotNull
    public String email;
    @NotNull
    public String password;
    @NotNull
    public String about;

    public String role;

    public List<Post> posts;

}
