package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Account;

import java.util.List;
import java.util.Optional;

@Service
public interface IAccountService extends IService<Account,String> {
    @Override
    List<Account> getAll();

    @Override
    Optional<Account> getById(String key);

    @Override
    Account save(Account entity);

    @Override
    void delete(Account entity);

    @Override
    void deleteById(String key);

    void changePassword(Account account);
}