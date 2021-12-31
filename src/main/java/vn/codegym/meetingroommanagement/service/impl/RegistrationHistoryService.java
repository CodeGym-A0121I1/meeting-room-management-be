package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.LocalTime;
import java.util.Date;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    IRegistrationHistoryRepository iRegistrationHistoryRepository;

    @Override
    public Page<RegistrationHistory> findAllByTimeContaining(Date startDay, Date endDay, Pageable pageable) {
        return iRegistrationHistoryRepository.findAllByTimeStartIsBetween(startDay, endDay, pageable);
    }
}