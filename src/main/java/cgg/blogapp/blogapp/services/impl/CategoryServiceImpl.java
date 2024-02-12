package cgg.blogapp.blogapp.services.impl;

import cgg.blogapp.blogapp.entities.Category;
import cgg.blogapp.blogapp.entities.CategoryDTO;
import cgg.blogapp.blogapp.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogapp.repos.CategoryRepository;
// import cgg.blogapp.blogapp.dao.CategoryRepository;
// import cgg.blogapp.blogapp.entities.Category;
// import cgg.blogapp.blogapp.exceptions.ResourceNotFoundException;
// import cgg.blogapp.blogapp.payloads.CategoryDTO;
import cgg.blogapp.blogapp.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    Category category = categoryDtoToCategory(categoryDTO);
    Category savedCategory = categoryRepository.save(category);
    return categoryToCategoryDto(savedCategory);
  }

  @Override
  public CategoryDTO updateCategory(int categoryId, CategoryDTO categoryDTO) {
    Category existingCategory = categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
    existingCategory.setCategoryTitle(categoryDTO.getCategoryTitle());
    existingCategory.setCategoryDescription(
        categoryDTO.getCategoryDescription());
    Category updatedCategory = categoryRepository.save(existingCategory);
    return categoryToCategoryDto(updatedCategory);
  }

  @Override
  public void deleteCategory(int categoryId) {
    categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
    categoryRepository.deleteById(categoryId);
  }

  @Override
  public CategoryDTO getCategory(int categoryId) {
    Category existingCategory = categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
    return categoryToCategoryDto(existingCategory);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    List<Category> allCategories = categoryRepository.findAll();
    List<CategoryDTO> categoryDTOs = allCategories
        .stream()
        .map(category -> categoryToCategoryDto(category))
        .collect(Collectors.toList());
    return categoryDTOs;
  }

  public Category categoryDtoToCategory(CategoryDTO categoryDTO) {
    return modelMapper.map(categoryDTO, Category.class);
  }

  public CategoryDTO categoryToCategoryDto(Category category) {
    return modelMapper.map(category, CategoryDTO.class);
  }

}
