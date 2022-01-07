package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.ChangePasswordRequest;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    //HuyTG
    //------------ update user ------------------
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> currentUser = userService.getById(user.getId());
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    //Thang DM
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    //LongLH

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/account/password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        boolean isSuccessful = this.accountService.changePassword(changePasswordRequest);

        if (isSuccessful) {
            return ResponseEntity.ok("Successful");
        } else {
            return ResponseEntity.badRequest().body("Wrong password");
        }
    }
}