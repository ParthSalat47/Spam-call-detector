package com.instahyre.truecaller.truecaller.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.repository.UserRepository;

@Service
public class LoginService {
    
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate_user_service(UserClass user)
    {
        ArrayList<UserClass> usersList = userRepository.getAllUsers();
        
        for(UserClass currentUser : usersList)
        {
            if(currentUser.getUserPhoneNumber() == user.getUserPhoneNumber())
            {
                if(currentUser.getUserPassword().equals(user.getUserPassword()))
                {
                    return true;
                }
            }
        }

        return false;
    }
}
