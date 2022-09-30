package com.example.firstaid.repository;

import com.example.firstaid.model.Question;
import com.example.firstaid.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepositoryJPA extends JpaRepository<Result,Long> {
}
