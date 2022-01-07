package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    private IRegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public List<RegistrationHistory> roomStatistic(String roomType, String roomName, String month, String year) {
        List<RegistrationHistory> registrationHistories = registrationHistoryRepository
                .roomStatistic(roomType, roomName, month, year);
        return registrationHistories;
    }

    public float registrationPerformance(String roomType, String roomName, String month, String year) {
        int m = Integer.parseInt(month);
        int y = Integer.parseInt(year);
        YearMonth yearMonth = YearMonth.of(y, m);
        LocalDate startMonth = yearMonth.atDay(1);
        LocalDate endMonth = yearMonth.atEndOfMonth();
        long totalTime = dayConversion(startMonth, endMonth) * 8 * 60 * 60;

        long totalUseTime = 0;
        List<RegistrationHistory> registrationHistories = this.roomStatistic(roomType, roomName, month, year);
        for (RegistrationHistory registrationHistory : registrationHistories) {
            YearMonth yearMonthStart = YearMonth.of(registrationHistory.getDateStart().getYear(), registrationHistory.getDateStart().getMonthValue());
            YearMonth yearMonthEnd = YearMonth.of(registrationHistory.getDateEnd().getYear(), registrationHistory.getDateEnd().getMonthValue());
            int dayConversion;
            long secondConversion;
            if (yearMonthEnd.compareTo(yearMonthStart) != 0) {
                dayConversion = dayConversion(registrationHistory.getDateStart(), yearMonthStart.atEndOfMonth());
            } else {
                dayConversion = dayConversion(registrationHistory.getDateStart(), registrationHistory.getDateEnd());
            }
            secondConversion = secondConversion(registrationHistory.getTimeStart(), registrationHistory.getTimeEnd());
            totalUseTime += dayConversion * secondConversion;
        }

        return (float) totalUseTime / totalTime;
    }

    @Override
    public int roomCountStatistic(String roomName) {
        return 0;
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
    public void delete(RegistrationHistory entity) {

    }

    @Override
    public void deleteById(String key) {
    }

    private int dayConversion(LocalDate dateStart, LocalDate dateEnd) {
        int dayConversion = Period.between(dateStart, dateEnd).getDays() + 1;
        return dayConversion;
    }

    private long secondConversion(LocalTime timeStart, LocalTime timeEnd) {
        long dayConversion = Duration.between(timeStart, timeEnd).getSeconds();
        return dayConversion;
    }
}
