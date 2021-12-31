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

    @PutMapping("/{username}")
    public ResponseEntity<?> changePassword(@PathVariable("username") String username,@RequestBody Account account) {
        Account currentAccount = accountService.getById(username);
        if (currentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentAccount.setPassword(account.getPassword());
        this.accountService.save(currentAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}