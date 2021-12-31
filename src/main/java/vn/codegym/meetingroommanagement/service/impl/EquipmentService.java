package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.repository.IEquipmentRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService implements IEquipmentService {

    @Autowired
    private IEquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> getById(String key) {
        return equipmentRepository.findById(key);
    }

    @Override
    public Equipment save(Equipment entity) {
        return equipmentRepository.save(entity);
    }

    @Override
    public void deleteById(String key) {
        equipmentRepository.deleteById(key);
    }

    @Override
    public void assignRoomForEquipment(Equipment equipment, Room room) {

    }
}