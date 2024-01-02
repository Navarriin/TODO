package com.example.demo.service;

import com.example.demo.dto.TODODTO;
import com.example.demo.model.TODO;
import com.example.demo.repository.TODORepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        List<TODODTO> todosList = repository.findAll().stream().map(TODODTO::new).toList();
        return todosList;
    };

    public ResponseEntity getById(Long id) {
        Optional<TODO> tododtoOptional = repository.findById(id);
        if(tododtoOptional.isPresent()){
            TODO todo = tododtoOptional.get();
            return ResponseEntity.ok(todo);
        } else{
          throw new EntityNotFoundException();
        }
    }
}
