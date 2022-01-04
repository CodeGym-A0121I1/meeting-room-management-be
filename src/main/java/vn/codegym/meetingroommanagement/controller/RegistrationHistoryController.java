package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.service.impl.RegistrationHistoryService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {
@Autowired
    RegistrationHistoryService registrationHistoryService;
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<List<?>> roomStatistic(@RequestParam("startDate") LocalDate startDate,
                                                 @RequestParam("endDate") LocalDate endDate) {
        List<?> registrationHistorys = registrationHistoryService.roomStatisticByTime(startDate, endDate);
        if (registrationHistorys == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistorys, HttpStatus.OK);
    }
}