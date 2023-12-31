package com.example.demo.service;

import com.example.demo.dto.TODODTO;
import com.example.demo.repository.TODORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TODOService {

    @Autowired
    private final TODORepository repository;
    public TODOService(TODORepository repository) {
        this.repository = repository;
    }

    public List<TODODTO> listAll() {
        List<TODODTO> todosList = repository.findAll().stream().map(TODODTO::new).toList();
        return todosList;
    };
}
