package vn.codegym.meetingroommanagement.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.service.ICategoryService;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryEquipmentController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/quantity")
    public ResponseEntity<List<CategoryQuantityStatusDTO>> getAllCategoryQuantityStatusDTO() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = this.categoryService.getAllCategoryQuantityStatusDTO();
        if (categoryQuantityStatusDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryQuantityStatusDTOList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAll();

        return categoryList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoryList);
    }


}