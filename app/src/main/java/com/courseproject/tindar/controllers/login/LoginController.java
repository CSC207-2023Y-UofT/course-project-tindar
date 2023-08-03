package com.courseproject.tindar.controllers.login;

import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

public class LoginController {



    public LoginController(){
    }

    public Boolean checkUserPassword(String email, String password){

        return true;
    }

    public String GetUserId(String email){
        return "1";
    }
}
