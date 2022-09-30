package com.example.firstaid.service;

import org.springframework.web.multipart.MultipartFile;

public interface InjuryService {
    void saveInjury(String injuryName, String context, MultipartFile urlImg,Integer category);
}
