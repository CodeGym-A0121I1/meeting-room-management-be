package vn.codegym.meetingroommanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.repository.EquipmentCategoryRepository;
import vn.codegym.meetingroommanagement.repository.EquipmentRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentCategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentCategoryService implements IEquipmentCategoryService {

    @Autowired
    private EquipmentCategoryRepository equipmentCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Category> findAll() {
        return this.equipmentCategoryRepository.findAll();
    }

    @Override
    public List<CategoryQuantityStatusDTO> findAllCategoryQuantityStatus() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = new ArrayList<>();
        for (Category category: this.findAll()) {
            CategoryQuantityStatusDTO categoryQuantityStatusDTO = modelMapper.map(category, CategoryQuantityStatusDTO.class);
            for (EStatus status : EStatus.values()) {
                int number = this.equipmentCategoryRepository.countByCategory_IdAndStatus(category.getId(), status.toString());
                if (status.toString().equals("FIXING")) {
                    categoryQuantityStatusDTO.setFixing(number);
                } else if (status.toString().equals("AVAILABLE")) {
                    categoryQuantityStatusDTO.setAvailable(number);
                } else {
                    categoryQuantityStatusDTO.setUsing(number);
                }
            }
            categoryQuantityStatusDTOList.add(categoryQuantityStatusDTO);
        }
        return categoryQuantityStatusDTOList;
    }

}
