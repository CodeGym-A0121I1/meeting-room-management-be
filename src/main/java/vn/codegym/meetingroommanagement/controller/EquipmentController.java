package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.IEquipmentCategoryService;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipment-management")
@CrossOrigin("http://localhost:4200")
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IEquipmentCategoryService categoryService;

    @GetMapping("/list-category-status")
    public ResponseEntity<List<CategoryQuantityStatusDTO>> listCategoryQuantityStatusDTO() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = this.categoryService.findAllCategoryQuantityStatus();
        return new ResponseEntity<>(categoryQuantityStatusDTOList, HttpStatus.OK);
    }

    @GetMapping("/list-equipment/{id}")
    public ResponseEntity<List<Equipment>> listEquipmentAllByCategoryIdResponseEntity(@PathVariable("id") int id) {
        List<Equipment> equipmentList = this.equipmentService.findAllByCategory_Id(id);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    @GetMapping("/list-equipment/{id}/{name}")
    public ResponseEntity<List<Equipment>> listEquipmentFindByCategoryIdAndNameLike(@PathVariable("id") int id, @PathVariable("name") String name) {
        List<Equipment> equipmentList = this.equipmentService.findAllByCategory_IdAndNameLike(id, name);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    @GetMapping("/list-category")
    public ResponseEntity<List<Category>> listCategoryResponseEntity() {
        List<Category> categoryList = this.categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/equipment/{id}")
    public ResponseEntity<Equipment> equipmentResponseEntity(@PathVariable("id") String id) {
        Equipment equipment = this.equipmentService.findById(id);
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<?> deleteEquipmentEntity(@PathVariable("id") String id) {
        Equipment equipment = this.equipmentService.findById(id);
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.equipmentService.delete(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/equipment/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") String id, @RequestBody Equipment equipment) {
        Equipment equipmentCheck = this.equipmentService.findById(id);
        if (equipmentCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.equipmentService.save(equipment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}