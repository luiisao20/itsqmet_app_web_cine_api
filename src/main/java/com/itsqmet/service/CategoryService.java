package com.itsqmet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.dto.CategoryDTO;
import com.itsqmet.entity.Category;
import com.itsqmet.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  private CategoryDTO mapToDTO(Category category) {
    CategoryDTO dto = new CategoryDTO();
    dto.setId(category.getId());
    dto.setName(category.getName());
    return dto;
  }

  private Category maptoEntity(CategoryDTO dto) {
    Category category = new Category();
    category.setId(dto.getId());
    category.setName(dto.getName());
    return category;
  }

  public List<CategoryDTO> findAll() {
    return categoryRepository.findAll()
        .stream()
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
        .map(m -> mapToDTO(m));
  }

  public CategoryDTO saveCategory(CategoryDTO category) {
    return mapToDTO(categoryRepository.save(maptoEntity(category)));
  }

  public CategoryDTO updateCategory(Long id, Category category) {
    Category oldCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

    oldCategory.setName(category.getName());

    Category categoryUpdated = categoryRepository.save(oldCategory);
    return mapToDTO(categoryUpdated);
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }

  @Transactional
  public CategoryDTO getCategoryWithMovie(Long id) {
    return findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
  }
}
