package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){

        return ResponseEntity.ok(roomService.getAll());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        room.setStatus(EStatus.AVAILABLE);
        return ResponseEntity.ok(roomService.save(room));
    }
}