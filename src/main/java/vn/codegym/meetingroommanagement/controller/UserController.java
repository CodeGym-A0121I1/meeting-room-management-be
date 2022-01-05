package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IUserService;
import java.util.List;
import java.util.Optional;
import vn.codegym.meetingroommanagement.model.user.Account;


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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> currentUser = userService.getById(id);
        if (currentUser.get() == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //Thang DM
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

  //THangDM

    @PutMapping("")
    public ResponseEntity<?> changePassword(@RequestBody Account account) {
        this.accountService.changePassword(account);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    //LongLH

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}