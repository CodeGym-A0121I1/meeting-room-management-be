package vn.codegym.meetingroommanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {

    private final IRegistrationHistoryService registrationHistoryService;

    public RegistrationHistoryController(IRegistrationHistoryService registrationHistoryService) {
        this.registrationHistoryService = registrationHistoryService;
    }

    @DeleteMapping(value = "/cancel/{id}")
    public ResponseEntity<RegistrationHistory> cancelRoomRegistration(@PathVariable("id") String id) {

        Optional<RegistrationHistory> optionalRegistrationHistory = registrationHistoryService.getById(id);

        if (!optionalRegistrationHistory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        optionalRegistrationHistory.get().setCancel(true);
        registrationHistoryService.save(optionalRegistrationHistory.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("static-by-time")
    public ResponseEntity<List<?>> staticByTime(@RequestParam("startDate") LocalDate startDate,
                                                @RequestParam("endDate") LocalDate endDate) {
        List<?> registrationHistories = registrationHistoryService.roomStatisticByTime(startDate, endDate);
        if (registrationHistories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }

    @GetMapping("static-by-room")
    public ResponseEntity<List<?>> staticByRoom(@RequestParam("roomType") String roomType,
                                                @RequestParam("roomName") String roomName,
                                                @RequestParam("month") String month,
                                                @RequestParam("year") String year) {
        List<?> registrationHistories = registrationHistoryService.roomStatistic(roomType, roomName, month, year);
        if (registrationHistories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }

    @GetMapping("static-room")
    public ResponseEntity<Integer> roomStatistic(@RequestParam("roomName") String roomName) {
        int registrationHistories;
        registrationHistories = registrationHistoryService.roomCountStatistic(roomName);
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }

    @GetMapping("static-room-id")
    public ResponseEntity<Integer> roomStatisticById(@RequestParam("roomId") String roomID) {
        int registrationHistories;
        registrationHistories = registrationHistoryService.roomCountStatisticById(roomID);
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }
}