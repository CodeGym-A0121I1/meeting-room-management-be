package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Account;


public interface IAccountService {
    void changePassword(Account account);
    Account getById(String username) ;
}