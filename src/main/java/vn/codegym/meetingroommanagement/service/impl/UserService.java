package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.model.user.ERole;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.repository.IUserRepository;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(IUserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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

    public List<String> getAllUsername() {
        return this.userRepository.getAllUsername();
    }

    @Override
    public List<User> search(String username, ERole role, String fullName, Integer departmentName) {
        return userRepository.search(username, role ,fullName,departmentName);
    }
}