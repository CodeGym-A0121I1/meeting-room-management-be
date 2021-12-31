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

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration-histories")
public class RegistrationHistoryController {
@Autowired
    RegistrationHistoryService registrationHistoryService;
@PostMapping("/time")
public ResponseEntity<RegistrationHistory> timeStatistic(@Validated @ModelAttribute("question")Date day1, Date day2, Pageable pageable){
    Page<RegistrationHistory> registrationHistory = registrationHistoryService.findAllByTimeContaining(day1,day2,pageable);
    return (ResponseEntity<RegistrationHistory>) registrationHistory.map(equipment -> new ResponseEntity<>(equipment, HttpStatus.OK));
}
}