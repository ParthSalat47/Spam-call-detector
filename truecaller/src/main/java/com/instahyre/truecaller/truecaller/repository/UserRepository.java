package com.instahyre.truecaller.truecaller.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.instahyre.truecaller.truecaller.models.UserClass;

@Component
public class UserRepository {
    
    private ArrayList<UserClass> usersList = new ArrayList<UserClass>();

    public boolean add_user_repository(UserClass user)
    {
        return usersList.add(user);
    }

    public ArrayList<UserClass> getAllUsers()
    {
        return usersList;
    }

}
