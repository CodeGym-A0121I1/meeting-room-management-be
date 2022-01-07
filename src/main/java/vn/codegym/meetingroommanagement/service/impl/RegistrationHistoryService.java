package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    IRegistrationHistoryRepository registrationHistoryRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<RegistrationHistory> roomStatistic(String roomType, String roomName, String month, String year) {
        List<RegistrationHistory> registrationHistories = registrationHistoryRepository.findAll();

        int m = Integer.parseInt(month);
        int y = Integer.parseInt(year);
        if (roomType != "") {
            registrationHistories = registrationHistories
                    .stream()
                    .filter(r -> r.getRoom().getRoomType().getName().equals(roomType))
                    .collect(Collectors.toList());
        }
        if (roomName != "") {
            registrationHistories = registrationHistories
                    .stream()
                    .filter(r -> r.getRoom().getName().equals(roomName))
                    .collect(Collectors.toList());
        }
        if (month != "") {
            registrationHistories = registrationHistories
                    .stream()
                    .filter(r -> (r.getDateStart().getMonthValue() == m) || (r.getDateEnd().getMonthValue() == m))
                    .collect(Collectors.toList());
        }
        if (year != "") {
            registrationHistories = registrationHistories
                    .stream()
                    .filter(r -> (r.getDateStart().getYear() == y) || (r.getDateEnd().getYear() == y))
                    .collect(Collectors.toList());
        }
        return registrationHistories;
    }

    @Override
    public int roomCountStatistic(String roomName) {
        return registrationHistoryRepository.roomCountStatistic(roomName);
    }

    @Override
    public List<RegistrationHistory> getAll() {
        return registrationHistoryRepository.findAll();
    }

    @Override
    public Optional<RegistrationHistory> getById(String key) {
        return Optional.empty();
    }

    @Override
    public RegistrationHistory save(RegistrationHistory entity) {
        return null;
    }

    @Override
    public void delete(RegistrationHistory entity) {

    }

    @Override
    public void deleteById(String key) {

    }
}
