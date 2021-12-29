package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.repository.IAccountRepository;
import vn.codegym.meetingroommanagement.service.IAccountService;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public void changePassword(Account account) {
        Account checkAccount = this.getById(account.getUsername());
        if (checkAccount != null) {
            this.accountRepository.save(account);
        }
    }

    @Override
    public Account getById(String username) {
        return this.accountRepository.findById(username).orElse(null);
    }
}