package vn.codegym.meetingroommanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.repository.ICategoryRepository;
import vn.codegym.meetingroommanagement.service.ICategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Integer key) {
        return categoryRepository.findById(key);
    }

    @Override
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer key) {
        categoryRepository.deleteById(key);
    }

    // thiết lập danh sách với các trường phù hợp từ Equipment và Category sang 1 đối tượng DTO
    @Override
    public List<CategoryQuantityStatusDTO> getAllCategoryQuantityStatusDTO() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = new ArrayList<>();
        for (Category category : this.getAll()) {
            CategoryQuantityStatusDTO categoryQuantityStatusDTO = modelMapper.map(category, CategoryQuantityStatusDTO.class);
            for (EStatus status : EStatus.values()) {
                int number = this.categoryRepository.countByCategory_IdAndStatus(category.getId(), status.toString());
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