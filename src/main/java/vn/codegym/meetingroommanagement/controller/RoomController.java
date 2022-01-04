package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;
import vn.codegym.meetingroommanagement.service.impl.RoomService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Qualifier("roomService")
    @Autowired
    IRoomService iRoomService;
//    update a room meeting
    @PutMapping(value = "/update/{id}", consumes={"application/json"})
    public ResponseEntity<Room> updateRoom(@PathVariable("id") String id, @RequestBody @Valid Room room, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Room>((Room) bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
      //find room by id in database
        Optional<Room> currentRoom = iRoomService.getById(id);
        if (!currentRoom.isPresent()) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        iRoomService.save(room);
        return new ResponseEntity<Room>(room,HttpStatus.OK);
    }
}