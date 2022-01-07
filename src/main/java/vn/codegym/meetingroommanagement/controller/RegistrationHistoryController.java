package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {

    @Autowired
    private IRegistrationHistoryService registrationHistoryService;

    @RequestMapping(value = "roomStatistic", method = RequestMethod.PUT)
    public ResponseEntity<List<RegistrationHistory>> roomStatistic(@RequestParam("roomType") String roomType,
                                                                   @RequestParam("roomName") String roomName,
                                                                   @RequestParam("month") String month,
                                                                   @RequestParam("year") String year) {
        List<RegistrationHistory> registrationHistorys = registrationHistoryService.roomStatistic(roomType, roomName, month, year);
        if (registrationHistorys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistorys, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Float> registrationPerformance(@RequestParam("roomType") String roomType,
                                                         @RequestParam("roomName") String roomName,
                                                         @RequestParam("month") String month,
                                                         @RequestParam("year") String year) {
        float registrationPerformance = registrationHistoryService.registrationPerformance(roomType, roomName, month, year);
        return new ResponseEntity<>(registrationPerformance, HttpStatus.OK);
    }

    @RequestMapping(value = "total", method = RequestMethod.PUT)
    public ResponseEntity<Integer> totalUseMeetingRoom(@RequestParam("roomName") String roomName) {
        int totalUse = registrationHistoryService.roomCountStatistic(roomName);
        return new ResponseEntity<>(totalUse, HttpStatus.OK);
    }
}
