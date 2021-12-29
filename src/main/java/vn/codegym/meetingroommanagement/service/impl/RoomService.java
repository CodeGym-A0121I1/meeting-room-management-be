package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.repository.IRoomRepository;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired
    IRoomRepository iRoomRepository;
    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public Optional<Room> getById(String key) {
        return iRoomRepository.findById(key);
    }

    @Override
    public Room save(Room entity) {
        return iRoomRepository.save(entity);
    }

    @Override
    public void delete(Room entity) {

    }

    @Override
    public void deleteById(String key) {

    }
}