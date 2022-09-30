package com.example.firstaid.repository;

import com.example.firstaid.model.Injury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InjuryRepositoryJPA extends JpaRepository<Injury, Long> {

}
