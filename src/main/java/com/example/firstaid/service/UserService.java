package com.example.firstaid.service;

import com.example.firstaid.model.Role;
import com.example.firstaid.model.User;
import com.example.firstaid.model.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    //se povikuva za da se otkrie dali nekoj korisnik postoi vo nashata baza,def vo UserDetailsService
    public UserDetails loadUserByUsername(String s) throws UserNotFoundException;
    User register(String username, String password, String repeatpassword, String name, String surname,
                  String email, Integer age);

}
