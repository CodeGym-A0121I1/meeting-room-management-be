package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    private IRegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public List<?> roomStatisticByTime(LocalDate startDate, LocalDate endDate) {
        return registrationHistoryRepository.roomStatistic(startDate, endDate);
    }

    @Override
    public List<?> roomStatistic(String roomType, String roomName, String month, String year) {
        return registrationHistoryRepository.roomStatistic(roomType, roomName, month, year);
    }

    @Override
    public int roomCountStatistic(String roomName) {
        return registrationHistoryRepository.roomCountStatistic(roomName);
    }

    @Override
    public List<RegistrationHistory> getAll() {
        return registrationHistoryRepository.findAll();
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
    public void deleteById(String key) {
        registrationHistoryRepository.deleteById(key);
    }
}