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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user){
        return service.register(user);
    }

    @GetMapping("/delete")
    public ResponseEntity<String> delete(){
        return service.delete();
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody UsersWrapper user){
        return service.editUser(user);
    }

    @GetMapping("/userDetails")
    public ResponseEntity<UsersWrapper> userControllerResponseEntity(){
        return service.userDetails();
    }

    @PostMapping("/editPassword")
    public ResponseEntity<String> editPassword(@RequestBody PasswordWrapper password){
        return service.editPassword(password);
    }

}
