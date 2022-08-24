package com.projects.backend.todo.service;

import com.projects.backend.todo.entity.Category;
import com.projects.backend.todo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findByTitle(String title, String email) {
        return categoryRepository.findByTitle(title, email);
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
