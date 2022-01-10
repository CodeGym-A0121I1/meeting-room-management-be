package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IRegistrationHistoryService extends IService<RegistrationHistory, String> {
    List<RegistrationHistory> statisticByTime(LocalDate startDate, LocalDate endDate);

    List<RegistrationHistory> statisticByRoom(String roomType, String roomName, String month, String year);

    float registrationPerformance(String roomType, String roomName, String month, String year);

    int roomCountStatistic(String roomType, String roomName, String month, String year);
}
