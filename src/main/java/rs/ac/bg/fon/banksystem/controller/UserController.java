package rs.ac.bg.fon.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.banksystem.auth.CustomAuthenticationManager;
import rs.ac.bg.fon.banksystem.model.User;
import rs.ac.bg.fon.banksystem.model.auth.AuthenticationResponse;
import rs.ac.bg.fon.banksystem.repository.UserRepository;
import rs.ac.bg.fon.banksystem.service.CustomUserDetailsService;
import rs.ac.bg.fon.banksystem.utils.JwtTokenUtil;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private JwtTokenUtil jwtUtility;
    @Autowired
    private CustomAuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public UserController() {

    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {

        AuthenticationResponse response = new AuthenticationResponse();
        User userFromDb = null;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String token = jwtUtility.generateToken(user);
            response.setJwtToken(token);
            response.setExpirationDate(jwtUtility.getExpirationDateFromToken(token));
            System.out.println("Expiration date: ");
            System.out.println(response.getExpirationDate());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(404).header("message", e.getMessage()).body(null);
        }


    }
}
