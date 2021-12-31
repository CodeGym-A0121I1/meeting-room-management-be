package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.repository.IAccountRepository;
import vn.codegym.meetingroommanagement.service.IAccountService;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> getById(String key) {
        return accountRepository.findById(key);
    }

    @Override
    public Account save(Account entity) {
        return null;
    }

    @Override
    public void delete(Account entity) {

    }

    @Override
    public void deleteById(String key) {

    }
}