package com.example.firstaid.service.impl;

import com.example.firstaid.model.Injury;
import com.example.firstaid.model.exception.InvalidArgumentException;
import com.example.firstaid.repository.InjuryRepositoryJPA;
import com.example.firstaid.service.InjuryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class InjuryServiceImpl implements InjuryService {
    private final InjuryRepositoryJPA injuryRepository;

    public InjuryServiceImpl(InjuryRepositoryJPA injuryRepository) {
        this.injuryRepository = injuryRepository;
    }

    @Override
    public void saveInjury(String injuryName, String context, MultipartFile urlImg, Integer category) {
        if(injuryName.isEmpty()||context.isEmpty())
            throw new InvalidArgumentException();
        Injury injury=new Injury(injuryName,context,category);

        String fileName = StringUtils.cleanPath(urlImg.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            if(urlImg.isEmpty())
                injury.setUrlImg(injury.getUrlImg());

            injury.setUrlImg(Base64.getEncoder().encodeToString(urlImg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.injuryRepository.save(injury);
    }
}
