package com.instahyre.truecaller.truecaller.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.instahyre.truecaller.truecaller.exceptionHandling.CustomException;
import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.repository.UserRepository;

@Service
public class SignupService {
    
    private final UserRepository userRepository;

    @Autowired
    public SignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean add_user_service(UserClass user) throws Exception
    {
        ArrayList<UserClass> usersList = userRepository.getAllUsers();
        
        for(UserClass currentUser : usersList)
        {
            //if phone number is already in database...
            if(currentUser.getUserPhoneNumber() == user.getUserPhoneNumber())
            {
                //...and the user is already registered, throw exception 
                if(currentUser.getUserName() != null)
                {
                    throw new CustomException(HttpStatus.CONFLICT, "Error! This phone number is already registered with us.");
                }

                //else simply update other details

                currentUser.setUserName(user.getUserName());
                currentUser.setUserPassword(user.getUserPassword());
                currentUser.setUserEmail(user.getUserEmail());

                return true;
            }
        }

        //else, explicitly create a new user
        return userRepository.add_user_repository(user);
    }

}
