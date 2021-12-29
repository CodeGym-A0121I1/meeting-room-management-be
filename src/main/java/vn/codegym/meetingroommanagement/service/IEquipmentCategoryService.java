package vn.codegym.meetingroommanagement.service;

import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

public interface IEquipmentCategoryService {

    List<Category> findAll();

    List<CategoryQuantityStatusDTO> findAllCategoryQuantityStatus();
}
