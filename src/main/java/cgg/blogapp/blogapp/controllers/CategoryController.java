package cgg.blogapp.blogapp.controllers;

import cgg.blogapp.blogapp.entities.CategoryDTO;
import cgg.blogapp.blogapp.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "din_scheme")
@CrossOrigin("*")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping("/")
  public ResponseEntity<CategoryDTO> createUser(
      @Valid @RequestBody CategoryDTO categoryDTO) {
    CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
    return new ResponseEntity<CategoryDTO>(createdCategory, HttpStatus.CREATED);
  }

  @PutMapping("/{catId}")
  public ResponseEntity<CategoryDTO> updateUser(
      @Valid @RequestBody CategoryDTO categoryDTO,
      @PathVariable("catId") int categoryId) {
    CategoryDTO updatedCategory = categoryService.updateCategory(
        categoryId,
        categoryDTO);
    return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
  }

  @GetMapping("/{catId}")
  public ResponseEntity<CategoryDTO> getCategory(
      @PathVariable("catId") int categoryId) {
    return ResponseEntity.ok(categoryService.getCategory(categoryId));
  }

  @GetMapping("/")
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    List<CategoryDTO> allCategories = categoryService.getAllCategories();
    return new ResponseEntity<List<CategoryDTO>>(allCategories, HttpStatus.OK);
  }

  @DeleteMapping("/{catId}")
  public ResponseEntity<ProblemDetail> deleteCategory(
      @PathVariable("catId") int categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity
        .of(
            ProblemDetail.forStatusAndDetail(
                HttpStatus.OK,
                "Category deleted successfully"))
        .build();
  }
}
