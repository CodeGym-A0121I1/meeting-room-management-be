package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public List<?> finall() {

        return registrationHistoryRepository.findAll();
    }

    @Override
    public Integer roomCountStatisticById(String roomId) {
        return registrationHistoryRepository.roomCountStatisticById(roomId);

    }

    @Override
    public List<RegistrationHistory> registrationHistoryByIsCancel() {
        //chưa check id user
        return registrationHistoryRepository.countRegistrationHistoryByIsCancel();
    }

    @Override
    public List<RegistrationHistory> REGISTRATION_HISTORY_LIST(String roomName, LocalDate dateStart, LocalDate dateEnd, EStatus status, Integer roomType) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<RegistrationHistory> registrationHistories = new ArrayList<>();
        if (dateStart == null) {
            dateStart = LocalDate.parse("2000-01-01", dateTimeFormatter);
        }

        if (dateEnd == null) {
            dateEnd = LocalDate.parse("3000-01-01", dateTimeFormatter);
        }

        if (roomName == null) {
            roomName = "";
        }

        if (dateEnd != null && dateStart != null && status != null && roomName != null) {
            registrationHistories = registrationHistoryRepository.searchRegistrationHistoryByNotRoomType(roomName, dateStart, dateEnd, status);
        } else {
            registrationHistories = registrationHistoryRepository.REGISTRATION_HISTORY_LIST(roomName, dateStart, dateEnd, status, roomType);
        }

        return registrationHistories;
        //  return registrationHistoryRepository.REGISTRATION_HISTORY_LIST(roomName,dateStart,dateEnd,status,roomType);

    }

    @Override
    public List<RegistrationHistory> findAllRegistrationHistoryByRoomId(String id) {
        return registrationHistoryRepository.findAllRegistrationHistoryByRoomId(id);
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