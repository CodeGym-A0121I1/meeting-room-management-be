package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IDepartmentService;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Qualifier("userService")
    @Autowired
    private IUserService iUserService;

    @Qualifier("accountService")
    @Autowired
    private IAccountService iAccountService;

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

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = iUserService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

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
        User newUser = iUserService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}