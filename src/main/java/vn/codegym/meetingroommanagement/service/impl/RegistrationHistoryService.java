package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.repository.IRegistrationHistoryRepository;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationHistoryService implements IRegistrationHistoryService {
    @Autowired
    private IRegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public List<RegistrationHistory> roomStatistic(String roomType, String roomName, String month, String year) {
        List<RegistrationHistory> registrationHistories = registrationHistoryRepository
                .roomStatistic(roomType, roomName, month, year);
        return registrationHistories;
    }

    public float registrationPerformance(String roomName, String month, String year, List<RegistrationHistory> registrationHistories) {
        float performance = 0;
        long totalTime = (timeConversion(month, year).getDays() + 1) * 8 * 60 * 60;
        long totalUseTime = 0;
        for (RegistrationHistory registrationHistory : registrationHistories) {
            int monthStart = registrationHistory.getDateStart().getMonthValue();
            int monthEnd = registrationHistory.getDateEnd().getMonthValue();
        }

        performance = (totalUseTime / totalTime);

        return performance;
    }

    @Override
    public int roomCountStatistic(String roomName) {
        return 0;
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
    public void delete(RegistrationHistory entity) {

    }

    @Override
    public void deleteById(String key) {
    }

    private Period timeConversion(String month, String year) {
        int m = Integer.parseInt(month);
        int y = Integer.parseInt(year);
        YearMonth yearMonth = YearMonth.of(y, m);
        LocalDate startMonth = yearMonth.atDay(1);
        LocalDate endMonth = yearMonth.atEndOfMonth();
        Period period = Period.between(startMonth, endMonth);
        return period;
    }
}
