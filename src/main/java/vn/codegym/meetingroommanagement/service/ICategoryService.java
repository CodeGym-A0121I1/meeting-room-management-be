package vn.codegym.meetingroommanagement.service;

import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll();

    List<CategoryQuantityStatusDTO> getAllCategoryQuantityStatusDTO();
}