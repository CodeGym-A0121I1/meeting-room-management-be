package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {
    @Autowired
    private IRegistrationHistoryService iRegistrationHistoryService;
    @DeleteMapping(value = "/cancel/{id}")
    public ResponseEntity<RegistrationHistory> cancelRoomRegistration(@PathVariable("id") String id){

        Optional<RegistrationHistory> optionalRegistrationHistory = iRegistrationHistoryService.getById(id);

        if (!optionalRegistrationHistory.isPresent()){
            return new ResponseEntity<RegistrationHistory>(HttpStatus.NOT_FOUND);
        }

        optionalRegistrationHistory.get().setCancel(true);
        iRegistrationHistoryService.save(optionalRegistrationHistory.get());
        return new ResponseEntity<RegistrationHistory>(HttpStatus.OK);
    }
}