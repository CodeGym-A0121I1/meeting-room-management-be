package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
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
        return registrationHistoryRepository.statisticByRoom(roomType, roomName, month, year);
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
        int m = Integer.parseInt(month);
        int y = Integer.parseInt(year);
        YearMonth yearMonth = YearMonth.of(y, m);
        LocalDate startMonth = yearMonth.atDay(1);
        LocalDate endMonth = yearMonth.atEndOfMonth();
        long totalTime = dayConversion(startMonth, endMonth) * 8 * 60 * 60;

        long totalUseTime = 0;
        List<RegistrationHistory> registrationHistories = this.statisticByRoom(roomType, roomName, month, year);
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
    public int roomCountStatistic(String roomType, String roomName, String month, String year) {
        int totalUse = 0;
        List<RegistrationHistory> registrationHistories = this.statisticByRoom(roomType, roomName, month, year);
        for (RegistrationHistory registrationHistory : registrationHistories) {
            YearMonth yearMonthStart = YearMonth.of(registrationHistory.getDateStart().getYear(), registrationHistory.getDateStart().getMonthValue());
            YearMonth yearMonthEnd = YearMonth.of(registrationHistory.getDateEnd().getYear(), registrationHistory.getDateEnd().getMonthValue());
            int dayConversion;
            if (yearMonthEnd.compareTo(yearMonthStart) != 0) {
                dayConversion = dayConversion(registrationHistory.getDateStart(), yearMonthStart.atEndOfMonth());
            } else {
                dayConversion = dayConversion(registrationHistory.getDateStart(), registrationHistory.getDateEnd());
            }
            totalUse += dayConversion;
        }

        return totalUse;
    }

    private int dayConversion(LocalDate dateStart, LocalDate dateEnd) {
        int dayConversion = Period.between(dateStart, dateEnd).getDays() + 1;
        return dayConversion;
    }

    private long secondConversion(LocalTime timeStart, LocalTime timeEnd) {
        long dayConversion = Duration.between(timeStart, timeEnd).getSeconds();
        return dayConversion;
    }

//    @Override
//    public List<RegistrationHistory> listSearch(String roomName)  {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            if(dateStart == null ){
//                dateStart = simpleDateFormat.parse("2000-01-01");
//            }
//            if (dateEnd == null){
//                dateEnd = simpleDateFormat.parse("3000-01-01");
//            }
//        }catch (ParseException parseException){
//            System.out.println(parseException.getMessage());
//        };
//
//        if (roomName == null){
//            roomName ="";
//        }
//        if (status == null){
//            status = "";
//        }
//        if (roomType == null){
//            roomType="";
//        }
//        return registrationHistoryRepository.REGISTRATION_HISTORY_LIST(roomName);

    // return registrationHistoryRepository.REGISTRATION_HISTORY_LIST(roomName,dateStart,dateEnd,status);
//}
}
