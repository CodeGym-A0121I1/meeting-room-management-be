package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

//    @Override
//    public int roomCountStatistic(String roomName) {
//        return registrationHistoryRepository.roomCountStatistic(roomName);
//    }

    @Override
    public List<?> finall() {

        return registrationHistoryRepository.findAll();
    }

    @Override
    public int roomCountStatisticById(String roomId) {
        Integer result = registrationHistoryRepository.roomCountStatisticById(roomId);
        return result == null ? 0 : result;
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

    public List<RegistrationHistory> statisticByRoom(String roomType, String roomName, String month, String year) {
        LocalDate timeStart;
        LocalDate timeEnd;
        if (month == "") {
            if (year == "") {
                timeStart = LocalDate.of(2015, 1, 1);
                timeEnd = LocalDate.of(2025, 1, 1);
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
                timeStart = LocalDate.of(2015, 1, 1);
                timeEnd = LocalDate.of(2025, 1, 1);
            } else {
                int y = Integer.parseInt(year);
                timeStart = LocalDate.of(y, 1, 1);
                timeEnd = LocalDate.of(y, 12, 31);
            }
        } else {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            YearMonth yearMonth = YearMonth.of(y, m);
            timeStart = yearMonth.atDay(1);
            timeEnd = yearMonth.atEndOfMonth();
        }
        totalTime = dayConversion(timeStart, timeEnd) * 8 * 60 * 60;

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
        LocalDate timeStart;
        LocalDate timeEnd;
        if (month == "") {
            if (year == "") {
                timeStart = LocalDate.of(2015, 1, 1);
                timeEnd = LocalDate.of(2025, 1, 1);
            } else {
                int y = Integer.parseInt(year);
                timeStart = LocalDate.of(y, 1, 1);
                timeEnd = LocalDate.of(y, 12, 31);
            }
        } else {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            YearMonth yearMonth = YearMonth.of(y, m);
            timeStart = yearMonth.atDay(1);
            timeEnd = yearMonth.atEndOfMonth();
        }
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