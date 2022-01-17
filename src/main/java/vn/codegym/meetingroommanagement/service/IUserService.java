package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.ERole;
import vn.codegym.meetingroommanagement.model.user.User;

import java.util.List;

@Service
public interface IUserService extends IService<User, String> {
    List<User> search(String username, ERole role,String fullName);
}