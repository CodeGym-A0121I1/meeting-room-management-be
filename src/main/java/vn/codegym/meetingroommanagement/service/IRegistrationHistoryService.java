package vn.codegym.meetingroommanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalTime;
import java.util.Date;

@Service
public interface IRegistrationHistoryService {
    Page<RegistrationHistory> findAllByTimeContaining(Date startDay, Date endDay, Pageable pageable);

}