package com.example.demo.service;

import com.example.demo.dto.TODODTO;
import com.example.demo.enums.Status;
import com.example.demo.model.TODO;
import com.example.demo.repository.TODORepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TODOService {

    @Autowired
    private final TODORepository repository;
    public TODOService(TODORepository repository) {
        this.repository = repository;
    }

    public List<TODODTO> getAll() {
        return repository.findAll()
                .stream().map(TODODTO::new)
                .filter(data -> data.status().equals(Status.ACTIVE))
                .sorted(((obj1, obj2) -> Long.compare(obj1.id(), obj2.id()))) // Ordenando a lista por Id
                .collect(Collectors.toList());
    }

    public ResponseEntity<TODO> getById(Long id) {
        Optional<TODO> tododtoOptional = repository.findById(id);

        if(tododtoOptional.isPresent()){
            TODO todo = tododtoOptional.get();
            return ResponseEntity.ok(todo);
        } else{
          throw new EntityNotFoundException();
        }
    }

    public ResponseEntity<TODO> createTodo(TODODTO body) {
       TODO todo = new TODO(body);

      try {
          repository.save(todo);
          return ResponseEntity.ok(todo);
      } catch (Exception err) {
          return ResponseEntity.badRequest().build();
      }
    }

    public ResponseEntity<TODO> updateTodo(Long id, TODODTO body) {
        Optional<TODO> todoOptional = repository.findById(id);

        if(todoOptional.isPresent()){
            TODO todo = todoOptional.get();
            todo.setId(body.id());
            todo.setContent(body.content());
            todo.setStatus(body.status());
            return ResponseEntity.ok(todo);
        }else {
            throw new EntityNotFoundException();
        }
    }

    public ResponseEntity<String> deleteTodo(Long id) {
          Optional<TODO> todoOptional = repository.findById(id);

          if(todoOptional.isPresent()){
              TODO todo = todoOptional.get();

              if(todo.getStatus().equals(Status.ACTIVE)){
                  repository.deleteById(id);
                  return ResponseEntity.ok("Task deleted successfully!");
              }else {
                  return ResponseEntity.badRequest().body("This id does not exist!");
              }
          }else {
              return ResponseEntity.badRequest().body("This id does not exist!");
          }
    }
}
