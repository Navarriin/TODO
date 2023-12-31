package com.example.demo.dto;

import com.example.demo.enums.Status;
import com.example.demo.model.TODO;

public record TODODTO(Long id, String content, Status status){
    public TODODTO (TODO todo){
        this(todo.getId(), todo.getContent(), todo.getStatus());
    }
}


