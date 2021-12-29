package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        List<Room> rooms = roomService.getAll();
        if (rooms.isEmpty()) {
            return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
    }
}