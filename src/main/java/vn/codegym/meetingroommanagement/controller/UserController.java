package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.ChangePasswordRequest;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.model.user.ERole;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.repository.IUserRepository;
import vn.codegym.meetingroommanagement.service.IAccountService;
import vn.codegym.meetingroommanagement.service.IDepartmentService;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final IUserService userService;

    private final IAccountService accountService;

    private final IDepartmentService departmentService;

    public UserController(IUserService userService, IAccountService accountService, IDepartmentService departmentService) {
        this.userService = userService;
        this.accountService = accountService;
        this.departmentService = departmentService;
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> currentUser = userService.getById(user.getId());
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> currentUser = userService.getById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (accountService.checkExistUsername(user.getAccount().getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<String>> getAllUsername() {
        List<String> listUsername = userService.getAllUsername();
        return new ResponseEntity<>(listUsername, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> search( @Param("username") String username ,@Param("role") ERole role ,@Param("fullName") String fullName ,@Param("departmentName") Integer departmentName ){
        List<User> userList = userService.search(username,role ,fullName ,departmentName);
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
}