package com.example.demo.dto;

import com.example.demo.model.TODO;

public record TODODTO(Long id, String content, Boolean status, Boolean active){
    public TODODTO (TODO todo){
        this(todo.getId(), todo.getContent(), todo.getStatus(), todo.getActive());
    }

    public TODODTO(TODODTO body) {
        this(body.id(), body.content(), body.status(), body.active());
    }
}


