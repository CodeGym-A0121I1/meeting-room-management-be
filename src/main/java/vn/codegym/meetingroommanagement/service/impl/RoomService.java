package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IRoomService;

@Service
public class RoomService implements IRoomService {
    @Override
    public Iterable<Room> findAll() {
        return null;
    }
}