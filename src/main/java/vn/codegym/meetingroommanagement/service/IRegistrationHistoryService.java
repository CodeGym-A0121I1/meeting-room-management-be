package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IRegistrationHistoryService extends IService<RegistrationHistory, String> {
    List<?> roomStatisticByTime(LocalDate startDate, LocalDate endDate);
  
    List<?> roomStatistic(String roomType, String roomName, String month, String year);

    int roomCountStatistic(String roomName);

    Integer roomCountStatisticById(String roomName);

    List<RegistrationHistory> registrationHistoryByIsCancel();

    List<RegistrationHistory> REGISTRATION_HISTORY_LIST(String roomName,LocalDate dateStart,LocalDate dateEnd, EStatus status, Integer roomType);


}