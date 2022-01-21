package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    private IRegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public List<RegistrationHistory> statisticByTime(LocalDate startDate, LocalDate endDate) {
        return registrationHistoryRepository.statisticByTime(startDate, endDate);
    }

    @Override
    public List<RegistrationHistory> statisticByRoom(String roomType, String roomName, String month, String year) {
        LocalDate timeStart;
        LocalDate timeEnd;
        if (month == "") {
            if (year == "") {
                timeStart = LocalDate.of(2000, 1, 1);
                timeEnd = LocalDate.of(2100, 1, 1);
            } else {
                int y = Integer.parseInt(year);
                timeStart = LocalDate.of(y, 1, 1).minusDays(1);
                timeEnd = LocalDate.of(y, 12, 31).plusDays(1);
            }
        } else {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            YearMonth yearMonth = YearMonth.of(y, m);
            timeStart = yearMonth.atDay(1).minusDays(1);
            timeEnd = yearMonth.atEndOfMonth().plusDays(1);
        }
        return registrationHistoryRepository.statisticByRoom(roomType, roomName, timeStart, timeEnd);
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

    public float registrationPerformance(String roomType, String roomName, String month, String year) {
        long totalTime = 0;
        LocalDate timeStart;
        LocalDate timeEnd;
        if (month == "") {
            if (year == "") {
                timeStart = LocalDate.of(2000, 1, 1);
                timeEnd = LocalDate.of(2100, 1, 1);
            } else {
                int y = Integer.parseInt(year);
                timeStart = LocalDate.of(y, 1, 1);
                timeEnd = LocalDate.of(y, 12, 31);
                if (Year.of(y).isLeap()) {
                    totalTime = 366 * 8 * 60 * 60;
                } else {
                    totalTime = 365 * 8 * 60 * 60;
                }
            }
        } else {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            YearMonth yearMonth = YearMonth.of(y, m);
            timeStart = yearMonth.atDay(1);
            timeEnd = yearMonth.atEndOfMonth();
            totalTime = dayConversion(timeStart, timeEnd) * 8 * 60 * 60;
        }

        long totalUseTime = 0;
        List<RegistrationHistory> registrationHistories = this.statisticByRoom(roomType, roomName, month, year);
        for (RegistrationHistory registrationHistory : registrationHistories) {
            LocalDate dateStart = registrationHistory.getDateStart();
            LocalDate dateEnd = registrationHistory.getDateEnd();
            long dayConversion = 0;
            long secondConversion;
            if (dateStart.compareTo(timeStart.minusDays(1)) > 0 && dateEnd.compareTo(timeEnd.plusDays(1)) < 0) {
                dayConversion = dayConversion(dateStart, dateEnd);
            } else if (dateStart.compareTo(timeStart.minusDays(1)) < 0 && dateEnd.compareTo(timeEnd.plusDays(1)) > 0) {
                dayConversion = dayConversion(timeStart, timeEnd);
            } else if (dateStart.compareTo(timeStart.minusDays(1)) < 0
                    && (dateEnd.compareTo(timeStart.minusDays(1)) > 0 && dateEnd.compareTo(timeEnd.plusDays(1)) < 0)) {
                dayConversion = dayConversion(timeStart, dateEnd);
            } else if ((dateStart.compareTo(timeStart.minusDays(1)) > 0 && dateStart.compareTo(timeEnd.plusDays(1)) < 0)
                    && dateEnd.compareTo(timeEnd.plusDays(1)) > 0) {
                dayConversion = dayConversion(dateStart, timeEnd);
            }
            secondConversion = secondConversion(registrationHistory.getTimeStart(), registrationHistory.getTimeEnd());
            totalUseTime += dayConversion * secondConversion;
        }

        return (float) totalUseTime / totalTime;
    }

    @Override
    public int roomCountStatistic(String roomType, String roomName, String month, String year) {
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        LocalDate timeStart;
        LocalDate timeEnd;
        YearMonth yearMonth = YearMonth.of(y, m);
        timeStart = yearMonth.atDay(1);
        timeEnd = yearMonth.atEndOfMonth();
        int totalUse = 0;
        List<RegistrationHistory> registrationHistories = this.statisticByRoom(roomType, roomName, month, year);
        for (RegistrationHistory registrationHistory : registrationHistories) {
            LocalDate dateStart = registrationHistory.getDateStart();
            LocalDate dateEnd = registrationHistory.getDateEnd();
            long dayConversion = 0;
            if (dateStart.compareTo(timeStart.minusDays(1)) > 0 && dateEnd.compareTo(timeEnd.plusDays(1)) < 0) {
                dayConversion = dayConversion(dateStart, dateEnd);
            } else if (dateStart.compareTo(timeStart.minusDays(1)) < 0 && dateEnd.compareTo(timeEnd.plusDays(1)) > 0) {
                dayConversion = dayConversion(timeStart, timeEnd);
            } else if (dateStart.compareTo(timeStart.minusDays(1)) < 0
                    && (dateEnd.compareTo(timeStart.minusDays(1)) > 0 && dateEnd.compareTo(timeEnd.plusDays(1)) < 0)) {
                dayConversion = dayConversion(timeStart, dateEnd);
            } else if ((dateStart.compareTo(timeStart.minusDays(1)) > 0 && dateStart.compareTo(timeEnd.plusDays(1)) < 0)
                    && dateEnd.compareTo(timeEnd.plusDays(1)) > 0) {
                dayConversion = dayConversion(dateStart, timeEnd);
            }
            totalUse += dayConversion;
        }

        return totalUse;
    }

    private long dayConversion(LocalDate dateStart, LocalDate dateEnd) {
        return ChronoUnit.DAYS.between(dateStart, dateEnd) + 1;
    }

    private long secondConversion(LocalTime timeStart, LocalTime timeEnd) {
        return Duration.between(timeStart, timeEnd).getSeconds();
    }

}
