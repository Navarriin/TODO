package com.example.demo.service;

import com.example.demo.dto.TODODTO;
import com.example.demo.model.TODO;
import com.example.demo.repository.TODORepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .filter(data -> data.status().equals(true))
                .sorted(((obj1, obj2) -> Long.compare(obj1.id(), obj2.id()))) // Ordenando a lista por Id
                .collect(Collectors.toList());
    }

    public TODO getById(Long id) {
        Optional<TODO> tododtoOptional = repository.findById(id);

        if(tododtoOptional.isPresent()){
            return tododtoOptional.get();
        } else{
          throw new EntityNotFoundException();
        }
    }

    public TODO createTodo(TODODTO body) {
        TODO todo = new TODO(body);
        return repository.save(todo);
    }

    public TODO updateTodo(Long id, TODODTO body) {
        Optional<TODO> todoOptional = repository.findById(id);

        if(todoOptional.isPresent()){
            TODO todo = todoOptional.get();
            todo.setId(body.id());
            todo.setContent(body.content());
            todo.setStatus(body.status());
            return todo;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public String deleteTodo(Long id) {
          Optional<TODO> todoOptional = repository.findById(id);
          final String successMessage = "Task deleted successfully!";
          final String errorMessage = "This id does not exist!";

          if(todoOptional.isPresent()){
              TODO todo = todoOptional.get();

              if(todo.getStatus().equals(true)){
                  repository.deleteById(id);
                  return successMessage;
              }else {
                  return errorMessage;
              }
          }else {
              return errorMessage;
          }
    }
}
