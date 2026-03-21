package com.itsqmet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsqmet.dto.CategoryDTO;
import com.itsqmet.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<CategoryDTO> getMethodName() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<CategoryDTO> getItem(@PathVariable Long id) {
    return categoryService.findById(id);
  }

  @PostMapping("/save")
  public CategoryDTO save(@RequestBody CategoryDTO category) {
    return categoryService.saveCategory(category);
  }

  @PutMapping("/update/{id}")
  public CategoryDTO update(@PathVariable Long id, @RequestBody CategoryDTO category) {
      return categoryService.updateCategory(id, category);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }

}
