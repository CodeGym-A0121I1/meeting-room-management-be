package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

@Service
public interface IEquipmentService extends IService<Equipment, String> {

    List<Equipment> getAllByCategory_Id(int id_category);

    List<Equipment> getAllByCategory_IdAndNameLike(int id_category, String nameEquipment);

}