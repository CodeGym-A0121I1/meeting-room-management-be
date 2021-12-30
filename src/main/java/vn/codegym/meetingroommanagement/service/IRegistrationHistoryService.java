package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

@Service
public interface IRegistrationHistoryService extends IService<RegistrationHistory,String> {
}