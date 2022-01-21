package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Account;

@Service
public interface IAccountService extends IService<Account, String> {
    boolean checkExistUsername(String username);
}