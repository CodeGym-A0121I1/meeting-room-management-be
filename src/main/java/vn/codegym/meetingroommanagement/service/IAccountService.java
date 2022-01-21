package vn.codegym.meetingroommanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.dto.ChangePasswordRequest;
import vn.codegym.meetingroommanagement.model.user.Account;

import java.util.List;
import java.util.Optional;

@Service
public interface IAccountService extends IService<Account, String>, UserDetailsService {
    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    boolean checkExistUsername(String username);
}