package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.User;
import vn.codegym.meetingroommanagement.repository.IUserRepository;
import vn.codegym.meetingroommanagement.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iuserRepository;

    @Override
    public List<User> getAll() {
        return iuserRepository.findAll();
    }

    @Override
    public Optional<User> getById(String key) {
        return iuserRepository.findById(key);
    }

    @Override
    public User save(User entity) {
        return iuserRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
        iuserRepository.delete(entity);
    }

    @Override
    public void deleteById(String key) {
        iuserRepository.deleteById(key);
    }
}