package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.util.List;

@Service
public interface IRegistrationHistoryService extends IService<RegistrationHistory, String> {
    List<?> roomStatistic(String roomType, String roomnName, String month, String year);

    List<?> roomTypeStatistic(String roomType, String month, String year);

    List<?> roomNameStatistic(String roomName, String month, String year);

    List<?> roomDateStatistic( String month, String year);

    List<?> roomTypeStatistic(String roomType);

    List<?> roomNameStatistic(String roomName);

    int roomCountStatistic(String roomName);
}

