package vn.codegym.meetingroommanagement.service;

import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

public interface IEquipmentService {

    List<Equipment> getAllByCategory_Id(int id_category);

    Equipment getById(String id);

    void deleteById(String id);

    void save(Equipment equipment);

    List<Equipment> getAllByCategory_IdAndNameLike(int id_category, String nameEquipment);

}