package com.instahyre.truecaller.truecaller.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instahyre.truecaller.truecaller.exceptionHandling.CustomException;
import com.instahyre.truecaller.truecaller.models.UserClass;
import com.instahyre.truecaller.truecaller.services.SearchService;

@RestController
@RequestMapping(path = "search")
public class SearchController {
    
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(path = "byName")
    public ArrayList<UserClass> searchByName(@RequestParam long userPhoneNumber, @RequestParam String userPassword, @RequestParam String searchName) throws Exception
    {
        UserClass user = new UserClass(null, userPhoneNumber, userPassword, null, 0);

        //if there are no users with the given name:
        if(searchService.searchByNameService(user, searchName).isEmpty())
        {
            throw new CustomException(HttpStatus.NOT_FOUND, "No users were found containing the given name.");
        }
        //else, simply return the arraylist
        else 
        {
            return searchService.searchByNameService(user, searchName);
        }

    }

    @GetMapping(path = "byNumber")
    public ArrayList<UserClass> searchByNumber(@RequestParam long userPhoneNumber, @RequestParam String userPassword, @RequestParam long searchNumber) throws Exception
    {
        UserClass user = new UserClass(null, userPhoneNumber, userPassword, null, 0);

        return searchService.searchByNumberService(user, searchNumber);
    }

}
