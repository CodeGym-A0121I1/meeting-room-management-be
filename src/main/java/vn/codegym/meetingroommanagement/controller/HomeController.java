package vn.codegym.meetingroommanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.jwt.JwtRequest;
import vn.codegym.meetingroommanagement.model.jwt.JwtResponse;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IUserService;
import vn.codegym.meetingroommanagement.utils.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final IAccountService accountService;

    private final IUserService userService;

    public HomeController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, IAccountService accountService, IUserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Meeting Room Management";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest) {
        String token = null;
        String userId = "";
        Optional<Account> account = Optional.empty();
        String status = "Login successful";
        UserDetails userDetails;
        HttpStatus httpStatus;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
            userDetails = accountService.loadUserByUsername(jwtRequest.getUsername());
            account = accountService.getById(jwtRequest.getUsername());
            userId = userService.getUserIdByAccount(jwtRequest.getUsername());
            token = jwtUtil.generateToken(userDetails);
            httpStatus = HttpStatus.OK;

        } catch (BadCredentialsException e) {
            status = "Wrong Password";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            status = "Not found user : " + jwtRequest.getUsername();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new JwtResponse(token, userId, account, status), httpStatus);
    }
}