package vn.codegym.meetingroommanagement.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.jwt.JwtRequest;
import vn.codegym.meetingroommanagement.model.jwt.JwtResponse;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.service.impl.AccountService;
import vn.codegym.meetingroommanagement.service.impl.MyUserDetailsService;
import vn.codegym.meetingroommanagement.utils.JwtUtil;

import java.util.Optional;

@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Meeting Room Managenment";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        String token = null;
        Optional<Account> account = null;
        String status=null;
        UserDetails userDetails = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new JwtResponse(token, account,e.toString());
        }

        userDetails = myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        account = accountService.getById(jwtRequest.getUsername());
        token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token, account,status);
    }
}
