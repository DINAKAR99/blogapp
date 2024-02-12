package cgg.blogapp.blogapp.services;

import java.util.List;

import cgg.blogapp.blogapp.entities.CategoryDTO;

public interface CategoryService {
  public CategoryDTO createCategory(CategoryDTO categoryDTO);

  public CategoryDTO updateCategory(int categoryId, CategoryDTO categoryDTO);

  public void deleteCategory(int categoryId);

  public CategoryDTO getCategory(int categoryId);

  public List<CategoryDTO> getAllCategories();
}
