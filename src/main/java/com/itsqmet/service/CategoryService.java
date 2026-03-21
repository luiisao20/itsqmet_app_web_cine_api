package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.CategoryDTO;
import com.itsqmet.entity.Category;
import com.itsqmet.repository.CategoryRepository;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category mapToEntity(CategoryDTO dto) {
    Category category = new Category();

    category.setId(dto.getId());
    category.setName(dto.getName());
    return category;
  }

  public CategoryDTO mapToDTO(Category category) {
    CategoryDTO dto = new CategoryDTO();

    dto.setId(category.getId());
    dto.setName(category.getName());

    return dto;
  }

  public List<CategoryDTO> findAll() {
    return categoryRepository.findAll().stream()
        .map(c -> mapToDTO(c))
        .collect(Collectors.toList());
  }

  public List<CategoryDTO> findAllById(List<Long> categoryIds) {
    return categoryRepository.findAllById(categoryIds)
        .stream()
        .map(c -> mapToDTO(c))
        .collect(Collectors.toList());
  }

  public Optional<CategoryDTO> findById(Long id) {
    return categoryRepository.findById(id)
        .map(c -> mapToDTO(c));
  }

  public CategoryDTO saveCategory(CategoryDTO category) {
    return mapToDTO(categoryRepository.save(mapToEntity(category)));
  }

  public CategoryDTO updateCategory(Long id, CategoryDTO category) {
    Category oldCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));

    oldCategory.setName(category.getName());

    return mapToDTO(categoryRepository.save(oldCategory));
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
