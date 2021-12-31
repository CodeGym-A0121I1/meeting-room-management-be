package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @Qualifier("roomService")
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

    // Detail a room in list
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoomById(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);
        if (!room.isPresent()) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Room>(room.get(), HttpStatus.OK);
    }

//    update a room meeting
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Room> updateRoom(@PathVariable("id") String id, @RequestBody Room room){
      //find room by id in database
        Optional<Room> currentRoom = roomService.getById(id);
        if (!currentRoom.isPresent()) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        roomService.save(room);
        return new ResponseEntity<Room>(room,HttpStatus.OK);


    }
}