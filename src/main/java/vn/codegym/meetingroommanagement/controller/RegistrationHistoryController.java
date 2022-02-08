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

    @GetMapping()
    public ResponseEntity<List<RegistrationHistory>> getAll() {
        List<RegistrationHistory> registrationHistoryList = registrationHistoryService.getAll();
        if (registrationHistoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RegistrationHistory>>(registrationHistoryList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistrationHistory> getRegistrationHistoryById(@PathVariable("id") String id) {
        Optional<RegistrationHistory> registrationHistory = registrationHistoryService.getById(id);
        if (!registrationHistory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RegistrationHistory>(registrationHistory.get(), HttpStatus.OK);
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
    public ResponseEntity<List<RegistrationHistory>> getListByIsCancel() {
        List<RegistrationHistory> registrationHistories = registrationHistoryService.registrationHistoryByIsCancel();
        if (registrationHistories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RegistrationHistory>>(registrationHistories, HttpStatus.OK);
    }

    @PutMapping("static-by-time")
    public ResponseEntity<List<RegistrationHistory>> staticByTime(@RequestParam("startDate") LocalDate startDate,
                                                                  @RequestParam("endDate") LocalDate endDate) {
        List<RegistrationHistory> registrationHistories = registrationHistoryService.statisticByTime(startDate, endDate);
        if (registrationHistories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);
    }

    @GetMapping(value = "static-by-room")
    public ResponseEntity<List<RegistrationHistory>> staticByRoom(@RequestParam("roomType") String roomType,
                                                                  @RequestParam("roomName") String roomName,
                                                                  @RequestParam("month") String month,
                                                                  @RequestParam("year") String year) {

        List<RegistrationHistory> registrationHistories = registrationHistoryService.statisticByRoom(roomType, roomName, month, year);
        if (registrationHistories.isEmpty()) {
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

//    @GetMapping("/search")
//    public ResponseEntity<List<RegistrationHistory>> registrationHistoryList(@RequestParam(value = "roomName", required = false) String name,
//                                                                             @RequestParam(value = "dateStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
//                                                                             @RequestParam(value = "dateEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd,
//                                                                             @RequestParam(value = "status", required = false) EStatus status,
//                                                                             @RequestParam(value = "roomType", required = false) Integer roomType
//
//    ) {
//
//        List<RegistrationHistory> registrationHistoryList = registrationHistoryService.REGISTRATION_HISTORY_LIST(name, start, dateEnd, status, roomType);
//    }

    @GetMapping("static-room-total")
    public ResponseEntity<Integer> totalUseMeetingRoom(@RequestParam("roomType") String roomType,
                                                       @RequestParam("roomName") String roomName,
                                                       @RequestParam("month") String month,
                                                       @RequestParam("year") String year) {
        int totalUse = registrationHistoryService.roomCountStatistic(roomType, roomName, month, year);
        return new ResponseEntity<>(totalUse, HttpStatus.OK);
    }

    @GetMapping("static-room-id")
    public ResponseEntity<Integer> roomStatisticById(@RequestParam("roomId") String roomID) {
        int registrationHistories = registrationHistoryService.roomCountStatisticById(roomID);
        return new ResponseEntity<>(registrationHistories, HttpStatus.OK);

    }

    //     Như đăng ký phòng họp
    @PostMapping("/signupRoom")
    public ResponseEntity<RegistrationHistory> createArea(@RequestBody RegistrationHistory history) {

        return ResponseEntity.ok(registrationHistoryService.save(history));
    }

    // show history
    @GetMapping(value = "/show")
    public ResponseEntity<List<?>> listAllCustomers() {
        System.out.println("đang list ra sao koddc ta");
        List<?> history = registrationHistoryService.finall();
//        System.out.println(registrationHistoryService.finall());
//        if (history.isEmpty()) {
//            return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<List<?>>(history, HttpStatus.OK);
    }
}