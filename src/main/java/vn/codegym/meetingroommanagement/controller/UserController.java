package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.service.IAccountService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IAccountService accountService;

    @PutMapping("")
    public ResponseEntity<?> changePassword(@RequestBody Account account) {
        this.accountService.changePassword(account);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }
}