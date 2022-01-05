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
    public List<?> roomStatistic(String roomType, String roomnName, String month, String year) {
        return registrationHistoryRepository.roomStatistic(roomType, roomnName, month, year);
    }

    @Override
    public List<?> roomTypeStatistic(String roomType, String month, String year) {
        return registrationHistoryRepository.roomTypeStatistic(roomType, month, year);
    }

    @Override
    public List<?> roomNameStatistic(String roomName, String month, String year) {
        return registrationHistoryRepository.roomNameStatistic(roomName, month, year);
    }

    @Override
    public List<?> roomDateStatistic(String month, String year) {
        return registrationHistoryRepository.roomDateStatistic(month, year);
    }

    @Override
    public List<?> roomTypeStatistic(String roomType) {
        return registrationHistoryRepository.roomTypeStatistic(roomType);
    }

    @Override
    public List<?> roomNameStatistic(String roomName) {
        return registrationHistoryRepository.roomNameStatistic(roomName);
    }

    @Override
    public int roomCountStatistic(String roomName) {
        return registrationHistoryRepository.roomStatistic(roomName);
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
