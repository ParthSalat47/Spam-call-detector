package com.instahyre.truecaller.truecaller.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.repository.UserRepository;

@Service
public class SpamService {
    
    private final UserRepository userRepository;
    private final LoginService loginService;

    @Autowired
    public SpamService(UserRepository userRepository, LoginService loginService) {
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

    public boolean report_number_service(UserClass user, long spamNumber)
    {
        //If the user was logged in, report the given number as spam
        if(loginService.authenticate_user_service(user))
        {
            ArrayList<UserClass> usersList = userRepository.getAllUsers();

            for(UserClass currentUser : usersList)
            {
                //If the phone number is present in DB, increase the spam count
                if(currentUser.getUserPhoneNumber() == spamNumber)
                {
                    //Increase the spam count by 1
                    currentUser.setSpamReports(currentUser.getSpamReports()+1);
                    return true;
                }
            }

            //else, create a pseudo user   
            UserClass spamUser = new UserClass(null, spamNumber, null, null, 1);
            userRepository.add_user_repository(spamUser);
            return true;
        }

        //If the user wasn't logged in, return false
        return false;
    }

}
