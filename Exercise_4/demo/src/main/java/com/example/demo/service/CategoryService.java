package com.example.demo.service;

import com.example.demo.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();

    public List<Category> getAllCategories() {
        return categories;
    }

    public Category getCategoryById(int id) {
        return categories.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void addCategory(Category category) {
        int maxId = categories.stream().mapToInt(Category::getId).max().orElse(0);
        category.setId(maxId + 1);
        categories.add(category);
    }

    public void updateCategory(Category category) {
        Category existing = getCategoryById(category.getId());
        if (existing != null) {
            existing.setName(category.getName());
        }
    }

    public void deleteCategory(int id) {
        categories.removeIf(c -> c.getId() == id);
    }
}
