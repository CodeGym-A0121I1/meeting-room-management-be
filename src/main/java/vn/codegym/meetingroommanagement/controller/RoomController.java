package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    // DanhDC xoá room
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Room> delete(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);
        if (!room.isPresent()) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        roomService.deleteById(id);
        return new ResponseEntity<Room>(HttpStatus.OK);
    }
}