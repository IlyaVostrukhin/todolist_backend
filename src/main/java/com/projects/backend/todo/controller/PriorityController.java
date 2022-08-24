package com.projects.backend.todo.controller;

import com.projects.backend.todo.dto.PrioritySearchDto;
import com.projects.backend.todo.entity.Priority;
import com.projects.backend.todo.service.PriorityService;
import com.projects.backend.todo.util.Checker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
@RequiredArgsConstructor
@Log4j2
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        try {
            Checker.idNotNullAndNotZero(priority.getId());
            Checker.titleNotNullAndNotEmpty(priority.getTitle());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.add(priority));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Priority priority) {

        try {
            Checker.idIsNullOrZero(priority.getId());
            Checker.titleNotNullAndNotEmpty(priority.getTitle());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        priorityService.update(priority);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            Checker.idIsNullOrZero(id);
            priorityService.delete(id);
        } catch (IllegalArgumentException argEx) {
            return new ResponseEntity(argEx.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (EmptyResultDataAccessException e) {
            String message = "Priority with id = " + id + " not found";
            log.error(message);
            return new ResponseEntity(message, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchDto prioritySearchDto) {

        try {
            Checker.emailNotNullAndNotEmpty(prioritySearchDto.getEmail());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        List<Priority> categories = priorityService.findByTitle(prioritySearchDto.getTitle(), prioritySearchDto.getEmail());

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/id")
    public ResponseEntity<Priority> findById(@RequestBody Long id) {

        try {
            Checker.idIsNullOrZero(id);
            return ResponseEntity.ok(priorityService.findById(id));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (NoSuchElementException e) {
            String message = "Priority with id = " + id + " not found";
            log.error(message);
            return new ResponseEntity(message, HttpStatus.NOT_ACCEPTABLE);
        }

    }
    
}
