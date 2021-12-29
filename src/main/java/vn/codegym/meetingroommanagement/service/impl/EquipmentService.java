package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.repository.IEquipmentRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {

    @Autowired
    private IEquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAllByCategory_Id(int id_category) {
        return this.equipmentRepository.findAllByCategory_Id(id_category);
    }

    @Override
    public Equipment getById(String id) {
        return this.equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        this.equipmentRepository.delete(this.getById(id));
    }

    @Override
    public void save(Equipment equipment) {
        this.equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> getAllByCategory_IdAndNameLike(int id_category, String nameEquipment) {
        return this.equipmentRepository.findAllByCategory_IdAndNameContaining(id_category, nameEquipment);
    }

}