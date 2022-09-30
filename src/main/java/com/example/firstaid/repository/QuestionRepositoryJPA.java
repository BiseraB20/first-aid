package com.example.firstaid.repository;

import com.example.firstaid.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepositoryJPA extends JpaRepository<Question,Long> {
}
