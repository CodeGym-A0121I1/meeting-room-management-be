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
    public Optional<User> getById(String id) {
        return iuserRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return iuserRepository.save(user);
    }

    @Override
    public void delete(User user) {
        iuserRepository.delete(user);
    }

    @Override
    public void deleteById(String id) {
        iuserRepository.deleteById(id);
    }
}