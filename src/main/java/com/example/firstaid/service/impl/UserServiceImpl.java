package com.example.firstaid.service.impl;


import com.example.firstaid.model.Role;
import com.example.firstaid.model.User;
import com.example.firstaid.model.exception.InvalidArgumentException;
import com.example.firstaid.model.exception.PasswordDoNotMatchException;
import com.example.firstaid.model.exception.UsernameExistsException;
import com.example.firstaid.repository.UserRepositoryJPA;
import com.example.firstaid.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryJPA userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepositoryJPA userRepository, PasswordEncoder passwordEncoder
                     ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    //implementacijata od AuthServiceImpl
    @Override
    public User register(String username, String password, String repeatpassword, String name, String surname, String email, Integer age) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email== null || email.isEmpty()
            || age==null || age==0)
            throw new InvalidArgumentException();

        //checking the password if it is equals to the repeated password
        if (!password.equals(repeatpassword)) {
            throw new PasswordDoNotMatchException();
        }
        //checking if there is another account with same username, the username is the id to the entity User
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameExistsException(username);
        }
        //setting the role depends on the age of the user
        Role role;
        if(age>=1 && age<=12){
             role=Role.ROLE_KID;
        }else{
             role=Role.ROLE_ADULT;
        }

        User user = new User(username, passwordEncoder.encode(password), name, surname, role, email,age);
        userRepository.save(user);//save the user using the jpa repository




        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
