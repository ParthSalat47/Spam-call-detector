package com.instahyre.truecaller.truecaller.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.instahyre.truecaller.truecaller.exceptionHandling.CustomException;
import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.repository.UserRepository;

@Service
public class SearchService {
    
    private final UserRepository userRepository;
    private final LoginService loginService;

    @Autowired
    public SearchService(UserRepository userRepository, LoginService loginService) {
        this.userRepository = userRepository;
        this.loginService = loginService; 
    }

    public ArrayList<UserClass> searchByNameService(UserClass user, String searchName) throws Exception
    {
        //If the user was logged in, search the given number
        if(loginService.authenticate_user_service(user))
        {
            ArrayList<UserClass> usersList = userRepository.getAllUsers();
            ArrayList<UserClass> startsWithSearchName = new ArrayList<UserClass>();
            ArrayList<UserClass> containsSearchName = new ArrayList<UserClass>();

            for(UserClass currentUser : usersList)
            {
                if(currentUser.getUserName() != null)
                {
                    if(currentUser.getUserName().startsWith(searchName))
                    {
                        //Remove password and email before saving:
                        currentUser.setUserPassword(null);
                        currentUser.setUserEmail(null);

                        startsWithSearchName.add(currentUser);
                    }
                    else if(currentUser.getUserName().contains(searchName))
                    {
                        //Remove password and email before saving:
                        currentUser.setUserPassword(null);
                        currentUser.setUserEmail(null);
                        
                        containsSearchName.add(currentUser);
                    }
                }
            }

            //return list containing first the users starting with that name,
            //then the users containing that search name.
            startsWithSearchName.addAll(containsSearchName);
            return startsWithSearchName;
        }
        //If the user wasn't logged in,
        else  
        {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Please login to search a user by name.");
        }
    }

    public ArrayList<UserClass> searchByNumberService(UserClass user, long searchNumber) throws Exception
    {
        //If the user was logged in, search the given number
        if(loginService.authenticate_user_service(user))
        {
            ArrayList<UserClass> usersList = userRepository.getAllUsers();
            ArrayList<UserClass> outputUsersList = new ArrayList<UserClass>();

            for(UserClass currentUser : usersList)
            {
                //if the phone number is in DB...
                if(currentUser.getUserPhoneNumber() == searchNumber)
                {
                    //...and if the user is registered
                    if(currentUser.getUserName() != null)
                    {
                        //Remove password and email before saving:
                        currentUser.setUserPassword(null);
                        currentUser.setUserEmail(null);

                        outputUsersList.add(currentUser);
                        return outputUsersList;
                    }
                    //if the user isn't registered, list all the otherNames
                    else  
                    {
                        for(String otherName : currentUser.getOtherNames())
                        {
                            UserClass newUser = new UserClass(otherName, searchNumber, null, null, currentUser.getSpamReports());

                            outputUsersList.add(newUser);
                        }

                        return outputUsersList;
                    }
                }
            }

            //if user not found, throw an Exception
            throw new CustomException(HttpStatus.NOT_FOUND, "No names were found for the given phone number.");
        }
        //If the user wasn't logged in,
        else  
        {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Please login to search a user by number.");
        }
    }

}
