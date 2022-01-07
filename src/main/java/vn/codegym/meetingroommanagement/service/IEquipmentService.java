package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.RoomType;

@Service
public interface IEquipmentService {
    Iterable<RoomType> findAll();

}