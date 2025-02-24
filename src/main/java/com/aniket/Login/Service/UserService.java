package com.aniket.Login.Service;

import com.aniket.Login.Model.PasswordWrapper;
import com.aniket.Login.Model.Users;
import com.aniket.Login.Model.UsersWrapper;
import com.aniket.Login.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public ResponseEntity<String> register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        repo.delete(repo.findByUsername(username));
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> editUser(UsersWrapper user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        Users user1 = repo.findByUsername(username);
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        repo.save(user1);

        return new ResponseEntity<>("User edited successfully", HttpStatus.OK);
    }

    public ResponseEntity<UsersWrapper> userDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Users user = repo.findByUsername(username);

        UsersWrapper user1 = new UsersWrapper();
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());

        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    public ResponseEntity<String> editPassword(PasswordWrapper password) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        String username = ((UserDetails) principal).getUsername();
        Users user = repo.findByUsername(username);

        System.out.println(password.getCurrentPassword());
        if (!encoder.matches(password.getCurrentPassword(), user.getPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }

        // Update the password after encoding
        user.setPassword(encoder.encode(password.getNewPassword()));
        repo.save(user);

        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }
}
