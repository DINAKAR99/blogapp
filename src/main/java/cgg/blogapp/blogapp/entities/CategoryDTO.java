package cgg.blogapp.blogapp.entities;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {

  private int categoryId;

  @NotBlank
  @Size(min = 4, message = "min size of category title is 4")
  private String categoryTitle;

  @NotBlank
  @Size(min = 10, message = "min size of category description is 10")
  private String categoryDescription;

  public List<Post> posts;
}
