package vn.codegym.meetingroommanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public interface IRegistrationHistoryService extends IService<RegistrationHistory, String> {
    List<RegistrationHistory> roomStatistic(String roomType, String roomName, String month, String year);

    float registrationPerformance(String roomName, String month, String year, List<RegistrationHistory> registrationHistories);

    int roomCountStatistic(String roomName);
}
