package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.service.IUserService;


import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Qualifier("IUserService")
    @Autowired
    private IUserService iUserService;

    //------------ update user ------------------

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> currentUser = iUserService.getById(id);

        if (currentUser.get() == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        iUserService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);


    }


}