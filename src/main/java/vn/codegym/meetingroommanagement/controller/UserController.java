package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.service.IAccountService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private IAccountService accountService;

    @PutMapping("")
    public ResponseEntity<?> changePassword(@RequestBody Account account) {
        Account currentAccount = accountService.getById(account.getUsername());
        if (currentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        this.accountService.save(currentAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}