package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

@Service
public interface IEquipmentService extends IService<Equipment, String> {

    List<Equipment> getAllByCategoryId(Integer idCategory);

    List<Equipment> getAllByCategoryIdAndNameLike(Integer idCategory, String nameEquipment);

}