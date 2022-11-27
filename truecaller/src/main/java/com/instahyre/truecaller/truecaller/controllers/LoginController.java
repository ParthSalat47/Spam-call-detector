package com.instahyre.truecaller.truecaller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.services.LoginService;

@RestController
@RequestMapping(path = "login")
public class LoginController {
    
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(path = "authenticate_user")
    public String add_user_controller(@RequestParam long userPhoneNumber, @RequestParam String userPassword)
    {
        UserClass user = new UserClass(null, userPhoneNumber, userPassword, null, 0);

        if(loginService.authenticate_user_service(user))
        {
            return "\nUser logged in successfully!";
        }
        else
        {
            return "\nIncorrect credentials. Please enter correct credentials.";
        }
    }

}
