package com.projects.backend.todo.controller;

import com.projects.backend.todo.entity.Category;
import com.projects.backend.todo.dto.CategorySearchDto;
import com.projects.backend.todo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Missing param: Id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().isEmpty()) {
            return new ResponseEntity("Missing param: TITLE must not be null or empty", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {

        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("Missing param: ID must not be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().isEmpty()) {
            return new ResponseEntity("Missing param: TITLE must not be null or empty", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            categoryService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            String message = "Category with id = " + id + " not found";
            log.error(message);
            return new ResponseEntity(message, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchDto categorySearchDto) {

        if (categorySearchDto.getEmail() == null || categorySearchDto.getEmail().trim().isEmpty()) {
            return new ResponseEntity("Missing param: EMAIL must not be null or empty", HttpStatus.NOT_ACCEPTABLE);
        }

        List<Category> categories = categoryService.findByTitle(categorySearchDto.getTitle(), categorySearchDto.getEmail());

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/id")
    public ResponseEntity<Category> findById(@RequestBody Long id) {

        if (id == null || id == 0) {
            return new ResponseEntity("Missing param: ID must not be null", HttpStatus.NOT_ACCEPTABLE);
        }

        Category category = null;

        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) {
            String message = "Category with id = " + id + " not found";
            log.error(message);
            return new ResponseEntity(message, HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

}
