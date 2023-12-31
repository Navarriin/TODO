package com.example.demo.repository;

import com.example.demo.model.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TODORepository extends JpaRepository<TODO, Long> {
}
