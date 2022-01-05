package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.repository.IRoomRepository;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;
    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(String key) {
        return Optional.empty();
    }

    @Override
    public Room save(Room entity) {
        return null;
    }

    @Override
    public void delete(Room entity) {

    }

    @Override
    public void deleteById(String key) {
        roomRepository.deleteById(key);
    }

    @Override
    public Page<Room> getAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }
}