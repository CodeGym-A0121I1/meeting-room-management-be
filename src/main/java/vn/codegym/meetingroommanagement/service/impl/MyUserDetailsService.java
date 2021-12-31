package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Account;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> account = accountService.getById(username);
        return new MyUserDetails(account);
    }
}
