package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.RoomType;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

@Service
public class EquipmentService implements IEquipmentService {


    @Override
    public Iterable<RoomType> findAll() {
        return null;
    }
}