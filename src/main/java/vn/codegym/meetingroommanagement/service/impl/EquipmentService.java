package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.repository.IEquipmentRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;
import vn.codegym.meetingroommanagement.service.IService;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService implements IEquipmentService {

    @Autowired
    IEquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAll() {
        return null;
    }

    @Override
    public Optional<Equipment> getById(String key) {
        return equipmentRepository.findById(key);
    }

    @Override
    public Equipment save(Equipment entity) {
        return null;
    }

    @Override
    public void delete(Equipment entity) {

    }

    @Override
    public void deleteById(String key) {

    }
}