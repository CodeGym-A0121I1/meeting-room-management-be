package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.User;

@Service
public interface IUserService extends IService<User, String>{
    void deleteUserById(String id);
}