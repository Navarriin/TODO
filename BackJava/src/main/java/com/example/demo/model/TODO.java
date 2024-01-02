package com.example.demo.model;

import com.example.demo.dto.TODODTO;
import com.example.demo.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE TODO SET status = 'Inativo' WHERE id = ?")
public class TODO {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 300)
    @Column(length = 300, nullable = false)
    private String content;

    private Status status = Status.ACTIVE;

    public TODO() {}

    public TODO(Long id, String content, Status status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public TODO(TODODTO body) {
        this.content = body.content();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
