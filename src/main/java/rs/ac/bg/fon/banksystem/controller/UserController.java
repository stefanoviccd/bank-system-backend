package rs.ac.bg.fon.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.banksystem.model.User;
import rs.ac.bg.fon.banksystem.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {


    @Autowired
    private UserRepository repository;

@PostMapping("/login")
@CrossOrigin
    public ResponseEntity<User>  login(@RequestBody User user){

    User response = null;
    try {
        response = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(response!=null)
        return ResponseEntity.ok(response);
        return ResponseEntity.status(404).header("message", "User does not exist.").body(null);

    } catch (Exception e) {
         return ResponseEntity.status(404).header("message", e.getMessage()).body(null);
    }



    }
}
