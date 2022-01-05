package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Room;

@Service
public interface IEquipmentService extends IService<Equipment, String> {

    void assignRoomForEquipment(Equipment equipment, Room room);
}