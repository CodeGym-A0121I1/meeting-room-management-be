package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.impl.EquipmentService;

import java.util.ArrayList;
import java.util.Optional;

import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
@CrossOrigin
public class EquipmentController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private EquipmentService equipmentService;

    // TrongVT
    // return List<CategoryQuantityStatus> : màn hình " Quản lý tài sản "
    // CategoryQuantityStatus là loại thiết bị và số lượng  của mỗi trạng thái
    // test in Postman OK
    @GetMapping("")
    public ResponseEntity<List<CategoryQuantityStatusDTO>> getAllCategoryQuantityStatusDTO() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = this.categoryService.getAllCategoryQuantityStatusDTO();
        if (categoryQuantityStatusDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryQuantityStatusDTOList, HttpStatus.OK);
    }

    // TrongVT
    // return: danh sách Category để dùng khi update hoặc create 1 Equipment
    // test in Postman OK
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = this.categoryService.getAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    // TrongVT
    // return : danh sách thiết bị thuộc 1 loại tài sản
    // id: id của đối tượng Category
    // test in Postman OK
    @GetMapping("/categories/{idCategory}")
    public ResponseEntity<List<Equipment>> getAllEquipmentByCategoryId(@PathVariable("idCategory") Integer idCategory) {
        List<Equipment> equipmentList = this.equipmentService.getAllByCategoryId(idCategory);
        if (equipmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // TrongVT
    // Xóa 1 đối tượng Equipment bằng id
    // test in Postman OK
    @DeleteMapping("/{id}")
    public ResponseEntity<Equipment> delete(@PathVariable("id") String id) {
        Optional<Equipment> equipmentOptional = this.equipmentService.getById(id);
        if (equipmentOptional.isPresent()) {
            this.equipmentService.deleteById(id);
            return new ResponseEntity<>(equipmentOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TrongVT
    // Cập nhật trường status của Equipment
    // kiểm tra Equipment đã tồn tại chưa, nếu tồn tại thì không thực hiện Cập nhật và trả về NOT_FOUND (ngược lại)
    // test in Postman OK
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable("id") String id, @RequestBody EStatus status) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Equipment> equipmentOptional = this.equipmentService.getById(id);
        if (equipmentOptional.isPresent()) {
            equipmentOptional.get().setStatus(status);
            this.equipmentService.save(equipmentOptional.get());
            return new ResponseEntity<>(equipmentOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TrongVT
    // return: danh sách Equipment khi tìm kiếm bằng name của Equipment
    // name: name của đuối tượng Equipment
    // idCategory: id của đối tượng Category
    // test in Postman OK
    @GetMapping("/{idCategory}/{name}")
    public ResponseEntity<List<Equipment>> search(@PathVariable("idCategory") Integer idCategory, @PathVariable("name") String name) {
        if (idCategory == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Equipment> equipmentList = this.equipmentService.getAllByCategoryIdAndNameLike(idCategory, name);
        if (equipmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

}