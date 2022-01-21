package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.model.user.Department;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.ChangePasswordRequest;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IDepartmentService;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    private final IAccountService accountService;

    public UserController(IUserService userService, IAccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> currentUser = userService.getById(user.getId());
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping
    @PostMapping("/add/account")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        if (iAccountService.checkExistUsername(account.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account newAccount = iAccountService.save(account);
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        }
    }

    @PostMapping("/add/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/account/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        boolean isSuccessful = this.accountService.changePassword(changePasswordRequest);

        if (isSuccessful) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @Qualifier("departmentService")
    @Autowired
    private IDepartmentService iDepartmentService;

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = iDepartmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<String>> getAllUsername() {
        List<String> listUsername = iUserService.getAllUsername();
        return new ResponseEntity<>(listUsername, HttpStatus.OK);
}