package com.example.demo.dto.mapper;

import com.example.demo.dto.TODODTO;
import com.example.demo.model.TODO;
import org.springframework.stereotype.Component;

@Component
public class TODOMapper {
    public static TODODTO toDto(TODO todo) {
        if(todo == null) {
            return null;
        }
        return new TODODTO(todo.getId(), todo.getContent(), todo.getStatus(), todo.getActive());
    }

    public TODO toEntity( TODODTO todoDto) {
        if(todoDto == null) {
            return null;
        }
        TODO todo = new TODO();
        if(todoDto.id() != null) {
            todo.setId(todoDto.id());
        }
        todo.setContent(todoDto.content());
        return todo;
    }
}
