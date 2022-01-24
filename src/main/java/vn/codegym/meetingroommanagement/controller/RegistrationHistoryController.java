package vn.codegym.meetingroommanagement.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {

    private final IRegistrationHistoryService registrationHistoryService;

    public RegistrationHistoryController(IRegistrationHistoryService registrationHistoryService) {
        this.registrationHistoryService = registrationHistoryService;
    }

    @GetMapping()
    public ResponseEntity<List<RegistrationHistory>> getAll(){
        List<RegistrationHistory> registrationHistoryList = registrationHistoryService.getAll();
        if (registrationHistoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RegistrationHistory>>(registrationHistoryList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistrationHistory> getRegistrationHistoryById(@PathVariable("id") String id) {
        Optional<RegistrationHistory> registrationHistory = registrationHistoryService.getById(id);
        if (!registrationHistory.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RegistrationHistory>(registrationHistory.get(),HttpStatus.OK);
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

        @GetMapping(value = "/getListRegistrationHistoryNotCancel") //đổi tên
    public ResponseEntity<List<RegistrationHistory>> getListByIsCancel(){
        List<RegistrationHistory> registrationHistories = registrationHistoryService.registrationHistoryByIsCancel();
        if (registrationHistories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RegistrationHistory>>(registrationHistories, HttpStatus.OK);
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
    @GetMapping("/search")
    public ResponseEntity<List<RegistrationHistory>> registrationHistoryList(@RequestParam(value = "roomName",required = false) String name,
                                                                             @RequestParam(value = "dateStart",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                           @RequestParam(value = "dateEnd",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd,
                                                                             @RequestParam(value = "status",required = false) EStatus status,
                                                                             @RequestParam(value = "roomType",required = false) Integer roomType

                                                                             ){

        List<RegistrationHistory> registrationHistoryList = registrationHistoryService.REGISTRATION_HISTORY_LIST(name,start,dateEnd,status,roomType);

        if (registrationHistoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistoryList, HttpStatus.OK);
    }

}