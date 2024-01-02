package com.example.demo.controller;

import com.example.demo.dto.TODODTO;
import com.example.demo.service.TODOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
public class TODOController {

   private final TODOService todoService;
    public TODOController(TODOService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TODODTO> List(){
        return todoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneById(@PathVariable Long id) {
        return todoService.getById(id);
    }

    @PostMapping
    public ResponseEntity createTodo(@RequestBody TODODTO body) {
        return todoService.createTodo(body);
    }
}
