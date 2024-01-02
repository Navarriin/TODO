package com.example.demo.controller;

import com.example.demo.dto.TODODTO;
import com.example.demo.model.TODO;
import com.example.demo.service.TODOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    };

    @GetMapping("/{id}")
    public ResponseEntity<TODODTO> getOneById(@PathVariable Long id) {
        return todoService.getById(id);
    }
}
