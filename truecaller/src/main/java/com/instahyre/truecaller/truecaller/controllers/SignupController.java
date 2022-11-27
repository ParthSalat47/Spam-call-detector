package com.instahyre.truecaller.truecaller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.services.SignupService;

@RestController
@RequestMapping(path = "signup")
public class SignupController {
    
    private final SignupService signupService;

    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping(path = "add_user")
    public String add_user_controller(@RequestParam String userName, @RequestParam long userPhoneNumber, @RequestParam String userPassword, @RequestParam(defaultValue = "This user has not set their Email address.") String userEmail) throws Exception
    {
        UserClass user = new UserClass(userName, userPhoneNumber, userPassword, userEmail, 0);

        if(signupService.add_user_service(user))
        {
            return "\nUser added successfully!";
        }
        else
        {
            return "\nFailed to add user. Please try again later.";
        }
    }

}
