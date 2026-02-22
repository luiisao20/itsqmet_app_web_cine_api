package com.itsqmet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsqmet.entity.Category;
import com.itsqmet.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public List<Category> findAllById(List<Long> categoryIds) {
    return categoryRepository.findAllById(categoryIds);
  }

  public Optional<Category> findById(Long id) {
    return categoryRepository.findById(id);
  }

  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Category updateCategory(Long id, Category category) {
    Category oldCategory = findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    oldCategory.setName(category.getName());
    return categoryRepository.save(oldCategory);
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }

  @Transactional
  public Category getCategoryWithMovie(Long id) {
    return findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
  }
}
