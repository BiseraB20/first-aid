package com.example.firstaid.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Injury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String injuryName;

    private String context;

    @Lob
    @Column(nullable = true)
    private String urlImg;

    //1-kids category, 2-adult category
    private Integer category;

    public Injury(){

    }
    public Injury(String injuryName, String context, Integer category) {
        this.injuryName = injuryName;
        this.context = context;
        this.category = category;
    }
}
