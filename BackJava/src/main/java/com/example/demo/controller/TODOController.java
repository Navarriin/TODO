package com.example.demo.controller;

import com.example.demo.dto.TODODTO;
import com.example.demo.model.TODO;
import com.example.demo.service.TODOService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    public ResponseEntity<List<TODODTO>> List(){
       try {
           List<TODODTO> getTodo = todoService.getAll();
           return ResponseEntity.ok().body(getTodo);
       } catch (Exception err) {
           return ResponseEntity.badRequest().build();
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TODO> getOneById(@PathVariable Long id) {
        TODO getOne = todoService.getById(id);
        return ResponseEntity.ok().body(getOne);
    }

    @PostMapping
    public ResponseEntity<TODO> createTodo(@RequestBody TODODTO body) {
        try {
            TODO postTodo = todoService.createTodo(body);
            return ResponseEntity.ok().body(postTodo);
        } catch (Exception err){
           return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TODO> updateTodo(@PathVariable Long id, @RequestBody TODODTO body) {
        TODO putTodo = todoService.updateTodo(id, body);
        return ResponseEntity.ok().body(putTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        String removeTodo = todoService.deleteTodo(id);
        return ResponseEntity.ok().body(removeTodo);
    }
}
