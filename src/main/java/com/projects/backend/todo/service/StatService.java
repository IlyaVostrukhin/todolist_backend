package com.projects.backend.todo.service;

import com.projects.backend.todo.entity.Stat;
import com.projects.backend.todo.repo.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StatService {

    private final StatRepository statRepository;

    public Stat findByEmail(String email) {
        return statRepository.findByUserEmail(email);
    }
}
