package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.RoomType;
import vn.codegym.meetingroommanagement.repository.IRoomTypeRepository;
import vn.codegym.meetingroommanagement.service.IRoomTypeService;

@Service
public class RoomTypeService implements IRoomTypeService {
    @Autowired
    private IRoomTypeRepository iRoomTypeRepository;
    @Override
    public Iterable<RoomType> findAll() {
        return this.iRoomTypeRepository.findAll();
    }
}
