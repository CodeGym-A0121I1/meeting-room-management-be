package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {
    @Autowired
    private IRegistrationHistoryService registrationHistoryService;

    @PostMapping("/signup-room")
    public ResponseEntity<RegistrationHistory> createRoom(@RequestBody RegistrationHistory history) {

        return ResponseEntity.ok(registrationHistoryService.save(history));
    }

    @GetMapping()
    public ResponseEntity<List<RegistrationHistory>> getAll() {
        List<RegistrationHistory> registrationHistories = registrationHistoryService.getAll();
        if (registrationHistories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RegistrationHistory>>(registrationHistories, HttpStatus.OK);
    }
  
    @DeleteMapping(value = "/cancel/{id}")
    public ResponseEntity<RegistrationHistory> cancelRoomRegistration(@PathVariable("id") String id) {

        Optional<RegistrationHistory> optionalRegistrationHistory = registrationHistoryService.getById(id);

        if (!optionalRegistrationHistory.isPresent()) {
            return new ResponseEntity<RegistrationHistory>(HttpStatus.NOT_FOUND);
        }

        optionalRegistrationHistory.get().setCancel(true);
        registrationHistoryService.save(optionalRegistrationHistory.get());
        return new ResponseEntity<RegistrationHistory>(HttpStatus.OK);
    }

    @PutMapping("static-by-time")
    public ResponseEntity<List<RegistrationHistory>> staticByTime(@RequestParam("startDate") LocalDate startDate,
                                                                  @RequestParam("endDate") LocalDate endDate) {
        List<RegistrationHistory> registrationHistories = registrationHistoryService.statisticByTime(startDate, endDate);
        if (registrationHistories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }

    @PutMapping("static-by-room")
    public ResponseEntity<List<RegistrationHistory>> staticByRoom(@RequestParam("roomType") String roomType,
                                                                  @RequestParam("roomName") String roomName,
                                                                  @RequestParam("month") String month,
                                                                  @RequestParam("year") String year) {
        List<RegistrationHistory> registrationHistories = registrationHistoryService.statisticByRoom(roomType, roomName, month, year);
        if (registrationHistories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }

    @GetMapping("static-room-performance")
    public ResponseEntity<Float> registrationPerformance(@RequestParam("roomType") String roomType,
                                                         @RequestParam("roomName") String roomName,
                                                         @RequestParam("month") String month,
                                                         @RequestParam("year") String year) {
        float registrationPerformance = registrationHistoryService.registrationPerformance(roomType, roomName, month, year);
        return new ResponseEntity<>(registrationPerformance, HttpStatus.OK);
    }

    @GetMapping("static-room-total")
    public ResponseEntity<Integer> totalUseMeetingRoom(@RequestParam("roomType") String roomType,
                                                       @RequestParam("roomName") String roomName,
                                                       @RequestParam("month") String month,
                                                       @RequestParam("year") String year) {
        int totalUse = registrationHistoryService.roomCountStatistic(roomType, roomName, month, year);
        return new ResponseEntity<>(totalUse, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<RegistrationHistory>> registrationHistoryList(@RequestParam("roomName") String name,
                                                                             @RequestParam("dateStart") Date dateStart,
                                                                             @RequestParam("dateEnd") Date dateEnd,
                                                                             @RequestParam("status") String status,
                                                                             @RequestParam("roomType") String roomType){

        List<RegistrationHistory> registrationHistoryList = registrationHistoryService.listSearch(name, dateStart, dateEnd, status,roomType);

        if (registrationHistoryList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistoryList, HttpStatus.OK);
    }
}

