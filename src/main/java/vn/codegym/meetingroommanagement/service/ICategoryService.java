package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;

import java.util.List;

@Service
public interface ICategoryService extends IService<Category, Integer> {

    List<CategoryQuantityStatusDTO> getAllCategoryQuantityStatusDTO();
}