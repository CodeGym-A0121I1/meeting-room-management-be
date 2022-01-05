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
    IRegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public List<?> roomStatistic(String roomType, String roomName, String month, String year) {
        if (roomType != null && roomName != null && month != null && year != null) {
            return registrationHistoryRepository.roomNameStatistic(roomName, month, year);
        } else if (roomName != null && month != null && year != null) {
            return registrationHistoryRepository.roomTypeStatistic(roomType, month, year);
        } else if (month != null && year != null) {
            return registrationHistoryRepository.roomDateStatistic(month, year);
        } else if (roomType != null && roomName != null) {
            return registrationHistoryRepository.roomNameStatistic(roomName);
        }
        return registrationHistoryRepository.roomTypeStatistic(roomType);
    }

    @Override
    public int roomCountStatistic(String roomName) {
        return registrationHistoryRepository.roomCountStatistic(roomName);
    }

    @Override
    public List<RegistrationHistory> getAll() {
        return null;
    }

    @Override
    public Optional<RegistrationHistory> getById(String key) {
        return Optional.empty();
    }

    @Override
    public RegistrationHistory save(RegistrationHistory entity) {
        return null;
    }

    @Override
    public void delete(RegistrationHistory entity) {

    }

    @Override
    public void deleteById(String key) {

    }
}
