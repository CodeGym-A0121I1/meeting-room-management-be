package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;
import vn.codegym.meetingroommanagement.service.impl.RoomService;

import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Qualifier("roomService")
    @Autowired
    IRoomService iRoomService;

//    update a room meeting
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Room> updateRoom(@PathVariable("id") String id, @RequestBody Room room){
      //find room by id in database
        Optional<Room> currentRoom = iRoomService.getById(id);
        if (!currentRoom.isPresent()) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        iRoomService.save(room);
        return new ResponseEntity<Room>(room,HttpStatus.OK);


    }
}