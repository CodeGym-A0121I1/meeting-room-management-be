package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.repository.EquipmentRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> findAllByCategory_Id(int id_category) {
        return this.equipmentRepository.findAllByCategory_Id(id_category);
    }

    @Override
    public Equipment findById(String id) {
        return this.equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Equipment equipment) {
        this.equipmentRepository.delete(equipment);
    }

    @Override
    public void save(Equipment equipment) {
        this.equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> findAllByCategory_IdAndNameLike(int id_category, String nameEquipment) {
        return this.equipmentRepository.findAllByCategory_IdAndNameContaining(id_category, nameEquipment);
    }

}