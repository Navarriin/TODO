package com.example.demo.model;

import com.example.demo.dto.TODODTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;

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

    private Boolean status = true;

    private Boolean active = true;

    public TODO() {}

    public TODO(Long id, String content, Boolean status, Boolean active) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.active = active;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
