package com.instahyre.truecaller.truecaller.models;

import java.util.ArrayList;

public class UserClass {
    
    private String userName;    //Used to check if a user is registered
    private long userPhoneNumber;
    private String userPassword;
    private String userEmail;
    private long spamReports;
    private ArrayList<String> otherNames = new ArrayList<String>(); //remove this?

    //Constructor without isRegistered
    public UserClass(String userName, long userPhoneNumber, String userPassword, String userEmail, long spamReports) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.spamReports = spamReports;
    }


    //Getters
    public String getUserName() {
        return userName;
    }
    
    public long getUserPhoneNumber() {
        return userPhoneNumber;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public long getSpamReports() {
        return spamReports;
    }

    public ArrayList<String> getOtherNames() {
        return otherNames;
    }
    

    //Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserPhoneNumber(long userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setSpamReports(long spamReports) {
        this.spamReports = spamReports;
    }
    
    public void setOtherNames(ArrayList<String> otherNames) {
        this.otherNames = otherNames;
    }

    //toString
    @Override
    public String toString() {
        return "UserClass [userName=" + userName + ", userPhoneNumber=" + userPhoneNumber + ", userPassword="
                + userPassword + ", userEmail=" + userEmail + ", spamReports=" + spamReports + "]";
    }

    

    

    

    
    

    

    

    

    

    

    
    

    


}
