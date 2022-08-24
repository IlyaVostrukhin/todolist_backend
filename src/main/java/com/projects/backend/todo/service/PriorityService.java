package com.projects.backend.todo.service;

import com.projects.backend.todo.entity.Priority;
import com.projects.backend.todo.repo.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }

    public List<Priority> findByTitle(String title, String email) {
        return priorityRepository.findByTitle(title, email);
    }

    public Priority add(Priority category) {
        return priorityRepository.save(category);
    }

    public Priority update(Priority category) {
        return priorityRepository.save(category);
    }

    public void delete(Long id) {
        priorityRepository.deleteById(id);
    }

}
