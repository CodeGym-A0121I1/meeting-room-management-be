package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.EStatus;
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

    private final IRoomRepository roomRepository;

    private final IEquipmentService equipmentService;

    public RoomService(IRoomRepository roomRepository, IEquipmentService equipmentService) {
        this.roomRepository = roomRepository;
        this.equipmentService = equipmentService;
    }

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
        entity.getEquipmentList().forEach(equipment -> equipmentService.getById(equipment.getId()).ifPresent(equipmentList::add));

        Room room = roomRepository.save(entity);

        equipmentList.forEach(equipment -> {
            equipment.setRoom(room);
            equipment.setStatus(EStatus.USING);
            equipmentService.save(equipment);
        });

        return room;
    }

    @Override
    public void deleteById(String key) {
        Optional<Room> roomOptional = getById(key);

        roomOptional.ifPresent(room -> {
            List<Equipment> equipmentList = new ArrayList<>();
            room.getEquipmentList().forEach(equipment -> equipmentService.getById(equipment.getId()).ifPresent(equipmentList::add));

            equipmentList.forEach(equipment -> {
                equipment.setRoom(null);
                equipment.setStatus(EStatus.AVAILABLE);
                equipmentService.save(equipment);
            });

            roomRepository.deleteById(key);
        });
    }

    @Override
    public String getNameForImage() {
        return "imageRoom" + roomRepository.getNumberOfRoom();
    }
}