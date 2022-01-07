package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.jwt.JwtRequest;
import vn.codegym.meetingroommanagement.model.jwt.JwtResponse;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.utils.JwtUtil;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Meeting Room Management";
    }

    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        String token = null;
        Optional<Account> account = null;
        String status = null;
        UserDetails userDetails = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new JwtResponse(token, account, "Password was wrong !");
        } catch (NoSuchElementException e) {
            return new JwtResponse(token, account, "Not found user: " + jwtRequest.getUsername());
        }

        userDetails = accountService.loadUserByUsername(jwtRequest.getUsername());
        account = accountService.getById(jwtRequest.getUsername());
        token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token, account, status);
    }
}