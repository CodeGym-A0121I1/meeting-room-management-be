package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.util.Optional;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {
    @Autowired
    private IRegistrationHistoryService registrationHistoryService;
  
    @DeleteMapping(value = "/cancel/{id}")
    public ResponseEntity<RegistrationHistory> cancelRoomRegistration(@PathVariable("id") String id){

        Optional<RegistrationHistory> optionalRegistrationHistory = registrationHistoryService.getById(id);

        if (!optionalRegistrationHistory.isPresent()){
            return new ResponseEntity<RegistrationHistory>(HttpStatus.NOT_FOUND);
        }

        optionalRegistrationHistory.get().setCancel(true);
        registrationHistoryService.save(optionalRegistrationHistory.get());
        return new ResponseEntity<RegistrationHistory>(HttpStatus.OK);
    }

    @RequestMapping(value = "roomStatistic",method = RequestMethod.PUT)
    public ResponseEntity<List<?>> roomStatistic(@RequestParam("roomType") String roomType,
                                                 @RequestParam("roomName") String roomName,
                                                 @RequestParam("month") String month,
                                                 @RequestParam("year") String year) {
        List<?> registrationHistorys = registrationHistoryService.roomStatistic(roomType, roomName, month, year);
        if (registrationHistorys == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistorys, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Integer> roomStatistic(@RequestParam("roomName") String roomName) {
        int registrationHistorys;
        registrationHistorys = registrationHistoryService.roomCountStatistic(roomName);
        return new ResponseEntity<>(registrationHistorys, HttpStatus.OK);
    }
}

