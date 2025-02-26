package com.aniket.Login.Controller;

import com.aniket.Login.Model.PasswordWrapper;
import com.aniket.Login.Model.Users;
import com.aniket.Login.Model.UsersWrapper;
import com.aniket.Login.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service;

    /*
       Handles User Registration
       Can be used by the user without authentication
       User has to send a json file with
       Username -: "aniket"
       Email -: "aniketbaravkar@gmail.com"
       Password -: "password"
    */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user){
        return service.register(user);
    }

    /*
        Deletes the current user if he
        wish to be delete his account
    */
    @GetMapping("/delete")
    public ResponseEntity<String> delete(){
        return service.delete();
    }

    /*
       Edits the user details Username or email
       User has to send the Json file with what he wants to edit
       "Username" : "username"
       "Email" : "email"
    */
    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody UsersWrapper user){
        return service.editUser(user);
    }

    /*
        Returns the user details username or email
    */
    @GetMapping("/userDetails")
    public ResponseEntity<UsersWrapper> userControllerResponseEntity(){
        return service.userDetails();
    }

    /*
       Edits the password of the user
       User has to send the Json file with the new password
       "currentPassword" : "password"
       "newPassword" : "newpassword"
    */
    @PostMapping("/editPassword")
    public ResponseEntity<String> editPassword(@RequestBody PasswordWrapper password){
        return service.editPassword(password);
    }

}