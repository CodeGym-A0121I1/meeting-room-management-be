package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.repository.IUserRepository;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(String key) {
        return userRepository.findById(key);
    }

    @Override
    public User save(User entity) {
        entity.getAccount().setPassword(bCryptPasswordEncoder.encode(entity.getAccount().getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(String key) {
        userRepository.deleteById(key);
    }
}