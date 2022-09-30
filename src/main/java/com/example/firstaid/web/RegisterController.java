package com.example.firstaid.web;

import com.example.firstaid.model.exception.InvalidArgumentException;
import com.example.firstaid.model.exception.PasswordDoNotMatchException;
import com.example.firstaid.service.AuthService;
import com.example.firstaid.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    //get mapping for getting the register form
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if (error != null && error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    //after clicked "REGISTER" button on the register form
    @Transactional
    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam Integer age,
                           @RequestParam(required = false) Long objId) {

        try {

            this.userService.register(username, password, repeatedPassword, name, surname, email, age);
            return "redirect:/home";

        } catch (InvalidArgumentException | PasswordDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }

}
