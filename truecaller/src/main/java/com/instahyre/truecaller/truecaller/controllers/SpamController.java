package com.instahyre.truecaller.truecaller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.services.SpamService;

@RestController
@RequestMapping(path = "spam")
public class SpamController {
    
    private final SpamService spamService;

    @Autowired
    public SpamController(SpamService spamService) {
        this.spamService = spamService;
    }

    @GetMapping(path = "report_number")
    public String report_number_controller(@RequestParam long userPhoneNumber, @RequestParam String userPassword, @RequestParam long spamNumber)
    {
        UserClass user = new UserClass(null, userPhoneNumber, userPassword, null, 0);
        
        if(spamService.report_number_service(user, spamNumber))
        {
            return "Number reported successfully!";
        }
        else  
        {
            return "Login first to report a number.";
        }
    }

}
