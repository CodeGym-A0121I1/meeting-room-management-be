package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

@Service
public interface IEquipmentService extends IService<Equipment, String> {

    List<Equipment> getAllByCategoryId(Integer id_category);

    List<Equipment> getAllByCategoryIdAndNameLike(Integer id_category, String nameEquipment);

    List<Equipment> findAllByNameContaining(String name);
}