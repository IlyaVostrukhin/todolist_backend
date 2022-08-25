package com.projects.backend.todo.controller;

import com.projects.backend.todo.entity.Stat;
import com.projects.backend.todo.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping("/stat")
    public ResponseEntity<Stat> getStat(@RequestBody String email) {
        return ResponseEntity.ok(statService.findByEmail(email));
    }

}
