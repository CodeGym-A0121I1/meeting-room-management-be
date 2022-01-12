package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.RoomType;
import vn.codegym.meetingroommanagement.repository.IRoomTypeRepository;
import vn.codegym.meetingroommanagement.service.IRoomTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService implements IRoomTypeService {

    @Autowired
    private IRoomTypeRepository roomTypeService;

    @Override
    public List<RoomType> getAll() {
        return roomTypeService.findAll();
    }

    @Override
    public Optional<RoomType> getById(Integer key) {
        return Optional.empty();
    }

    @Override
    public RoomType save(RoomType entity) {
        return null;
    }

    @Override
    public void deleteById(Integer key) {

    }
}