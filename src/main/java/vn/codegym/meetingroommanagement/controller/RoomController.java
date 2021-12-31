package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;


import java.util.Optional;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    // DanhDC xoá 1 room
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Room> delete(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);
        if (!room.isPresent()) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        roomService.deleteById(id);
        return new ResponseEntity<Room>(HttpStatus.OK);
    }
}