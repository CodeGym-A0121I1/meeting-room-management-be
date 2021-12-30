package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    private IRegistrationHistoryRepository registrationHistoryRepository;
    @Override
    public List<RegistrationHistory> getAll() {
        return null;
    }

    @Override
    public Optional<RegistrationHistory> getById(String key) {
        return Optional.ofNullable(this.registrationHistoryRepository.findById(key).orElse(null));
    }

    @Override
    public RegistrationHistory save(RegistrationHistory entity) {
        return this.registrationHistoryRepository.save(entity);
    }

    @Override
    public void delete(RegistrationHistory entity) {

    }

    @Override
    public void deleteById(String key) {

    }
}