package com.example.firstaid.service;

import com.example.firstaid.model.User;

public interface AuthService {
    User login(String username, String password);

}