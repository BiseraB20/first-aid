package com.example.firstaid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidate_name;

    private String candidate_surname;

    private Integer score;

    @OneToOne(mappedBy = "certificate")
    private User user;

    public Certificate(){

    }
}
