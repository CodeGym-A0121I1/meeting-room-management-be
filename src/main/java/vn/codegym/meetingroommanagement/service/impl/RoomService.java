package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.repository.IRoomRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IEquipmentService equipmentService;

    @Override
    public List<Room> getAll() {
       return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(String key) {
        return roomRepository.findById(key);
    }

    @Override
    public Room save(Room entity) {

        List<Equipment> equipmentList = new ArrayList<>();
        entity.getEquipmentList().forEach(equipment -> {
            equipmentService.getById(equipment.getId()).ifPresent(equipmentList::add);
        });

        Room room = roomRepository.save(entity);

        equipmentList.forEach(equipment -> {
            equipment.setRoom(room);
            equipmentService.save(equipment);
        });

        return room;
    }

    @Override
    public void deleteById(String key) {
        roomRepository.deleteById(key);
    }
}