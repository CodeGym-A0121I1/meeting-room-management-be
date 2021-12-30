package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.impl.EquipmentService;

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
    EquipmentService equipmentService;

    // TrongVT
    // return List<CategoryQuantityStatus> : màn hình " Quản lý tài sản "
    // CategoryQuantityStatus là loại thiết bị và số lượng  của mỗi trạng thái
    // test in Postman OK
    @GetMapping("")
    public ResponseEntity<List<CategoryQuantityStatusDTO>> getAllCategoryQuantityStatusDTO() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = this.categoryService.getAllCategoryQuantityStatusDTO();
        return new ResponseEntity<>(categoryQuantityStatusDTOList, HttpStatus.OK);
    }

    // TrongVT
    // return : danh sách thiết bị thuộc 1 loại tài sản
    // id: id của đối tượng Category
    // test in Postman OK
    @GetMapping("/{idCategory}")
    public ResponseEntity<List<Equipment>> getAllEquipmentByCategoryId(@PathVariable("idCategory") int idCategory) {
        List<Equipment> equipmentList = this.equipmentService.getAllByCategory_Id(idCategory);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // TrongVT
    // Xóa 1 đối tượng Equipment bằng id
    // test in Postman OK
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        Optional<Equipment> equipment = this.equipmentService.getById(id);
        if (equipment.isPresent()) {
            this.equipmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TrongVT
    // Cập nhật Equipment
    // kiểm tra Equipment đã tồn tại chưa, nếu tồn tại thì không thực hiện Cập nhật và trả về NOT_FOUND (ngược lại)
    // test in Postman OK
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Equipment equipment) {
        Optional<Equipment> equipmentOptional = this.equipmentService.getById(equipment.getId());
        if (equipmentOptional.isPresent()) {
            this.equipmentService.save(equipment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TrongVT
    // return: danh sách Equipment khi tìm kiếm bằng name của Equipment
    // name: name của đuối tượng Equipment
    // idCategory: id của đối tượng Category
    // test in Postman OK
    @GetMapping("/{idCategory}/{name}")
    public ResponseEntity<List<Equipment>> search(@PathVariable("idCategory") int idCategory, @PathVariable("name") String name) {
        List<Equipment> equipmentList = this.equipmentService.getAllByCategory_IdAndNameLike(idCategory, name);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // TrongVT
    // return: danh sách Category để dùng khi update hoặc create 1 Equipment
    // test in Postman OK
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = this.categoryService.getAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    // DatNT
    // Thêm mới 1 Equipment
    // test in Postman OK
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Equipment equipment) {
        equipmentService.save(equipment);
        return ResponseEntity.ok().body(equipment);
    }

    // DatNT
    // return: đối tượng Equipment
    // id: id của đối tượng Equipment
    // test in Postman OK
    @GetMapping("/equipment/{id}")
    public ResponseEntity<Equipment> getById(@PathVariable("id") String id) {
        Optional<Equipment> equipmentOptional = equipmentService.getById(id);
        return equipmentOptional.map(equipment -> new ResponseEntity<>(equipment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}