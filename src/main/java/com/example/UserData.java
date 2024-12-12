package com.example;

public class UserData {
    // dataFile.writeInFile(regName, regPassword, regEmail, regAge);
    String userName;
    String userPassword;
    String userEmail;
    
    String school;
    String movie;

    public UserData(String userName, String userPassword, String userEmail, String school,
            String movie) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        
        this.school = school;
        this.movie = movie;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

   

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

   

    public void setSchool(String school) {
        this.school = school;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSchool() {
        return school;
    }

    public String getMovie() {
        return movie;
    }

}
