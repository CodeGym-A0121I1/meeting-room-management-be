package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.repository.IEquipmentRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService implements IEquipmentService {

    @Autowired
    private IEquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getAllByCategoryId(Integer idCategory) {
        return this.equipmentRepository.findAllByCategory_Id(idCategory);
    }

    @Override
    public List<Equipment> getAllByCategoryIdAndNameLike(Integer idCategory, String nameEquipment) {
        return this.equipmentRepository.findAllByCategoryIdAndNameContaining(idCategory, nameEquipment);
    }

    @Override
    public List<Equipment> getAll() {
        return this.equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> getById(String id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }
    
    @Override
    public void delete(Equipment entity) {

    }

    @Override
    public void deleteById(String id) {
        this.equipmentRepository.delete(this.equipmentRepository.getById(id));
    }
    
}
