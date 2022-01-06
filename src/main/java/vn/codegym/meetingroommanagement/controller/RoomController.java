package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoom() {
        List<Room> rooms = roomService.getAll();
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        room.setStatus(EStatus.AVAILABLE);

        return new ResponseEntity<>(roomService.save(room), HttpStatus.CREATED);
    }

    // Detail a room in list
    @GetMapping(value = "/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);

        return room.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //    update a room meeting
    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        Optional<Room> roomOptional = roomService.getById(room.getId());

        return roomOptional.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    // DanhDC xoá room
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);

        return room.map(r -> {
            roomService.deleteById(id);

            return ResponseEntity.ok("Delete Successful");
        }).orElseGet(ResponseEntity.notFound()::build);

    }
}