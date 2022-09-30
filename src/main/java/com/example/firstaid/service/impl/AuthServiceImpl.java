package com.example.firstaid.service.impl;

import com.example.firstaid.model.User;
import com.example.firstaid.model.exception.InvalidArgumentException;
import com.example.firstaid.model.exception.InvalidUserCredentialException;
import com.example.firstaid.repository.UserRepositoryJPA;
import com.example.firstaid.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepositoryJPA userRepository;

    public AuthServiceImpl(UserRepositoryJPA userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException();
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialException::new);
        //ako ne postoi takov korisnik togahs neka se frli nekoj exception shto soodvestuva
    }

}
