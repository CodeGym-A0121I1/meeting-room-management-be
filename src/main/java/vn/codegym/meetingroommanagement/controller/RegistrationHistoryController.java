package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {
    @Autowired
    private IRegistrationHistoryService registrationHistoryService;

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<List<?>> roomStatistic(@RequestParam("roomType") String roomType,
                                                 @RequestParam("roomName") String roomName,
                                                 @RequestParam("month") Integer month,
                                                 @RequestParam("year") Integer year) {
        List<?> registrationHistorys = registrationHistoryService.roomStatistic(roomType, roomName, month, year);
        int countRoomName = registrationHistoryService.roomStatistic(roomName);
        if (registrationHistorys == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistorys, HttpStatus.OK);
    }
}
