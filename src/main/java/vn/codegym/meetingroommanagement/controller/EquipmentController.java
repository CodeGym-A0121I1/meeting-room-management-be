package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.ICategoryService;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;


@RestController
@RequestMapping("/api/equipments")
@CrossOrigin
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private ICategoryService categoryService;

    // return List<CategoryQuantityStatus> : màn hình " Quản lý tài sản "
    // CategoryQuantityStatus là loại thiết bị và số lượng  của mỗi trạng thái
    // test in Postman OK
    @GetMapping("")
    public ResponseEntity<List<CategoryQuantityStatusDTO>> getAllCategoryQuantityStatusDTO() {
        List<CategoryQuantityStatusDTO> categoryQuantityStatusDTOList = this.categoryService.getAllCategoryQuantityStatusDTO();
        return new ResponseEntity<>(categoryQuantityStatusDTOList, HttpStatus.OK);
    }

    // return : danh sách thiết bị thuộc 1 loại tài sản
    // id: id của đối tượng Category
    // test in Postman OK
    @GetMapping("/{idCategory}")
    public ResponseEntity<List<Equipment>> getAllEquipmentByCategoryId(@PathVariable("idCategory") int idCategory) {
        List<Equipment> equipmentList = this.equipmentService.getAllByCategory_Id(idCategory);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // Xóa 1 đối tượng Equipment bằng id
    // test in Postman OK
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEquipmentEntity(@PathVariable("id") String id) {
        Equipment equipment = this.equipmentService.getById(id);
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.equipmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // Cập nhật Equipment
    // kiểm tra Equipment đã tồn tại chưa, nếu tồn tại thì không thực hiện Cập nhật và trả về NOT_FOUND (ngược lại)
    // test in Postman OK
    @PutMapping("{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") String id, @RequestBody Equipment equipment) {
        Equipment equipmentCheck = this.equipmentService.getById(id);
        if (equipmentCheck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.equipmentService.save(equipment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // return: danh sách Equipment khi tìm kiếm bằng name của Equipment
    // name: name của đuối tượng Equipment
    // idCategory: id của đối tượng Category
    // test in Postman OK
    @GetMapping("/{idCategory}/{name}")
    public ResponseEntity<List<Equipment>> searchEquipmentByCategoryIdAndNameLike(@PathVariable("idCategory") int idCategory, @PathVariable("name") String name) {
        List<Equipment> equipmentList = this.equipmentService.getAllByCategory_IdAndNameLike(idCategory, name);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // return: danh sách Category để dùng khi update hoặc create 1 Equipment
    // test in Postman OK
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> listCategoryResponseEntity() {
        List<Category> categoryList = this.categoryService.getAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    // return: đối tượng Equipment
    // id: id của đối tượng Equipment
    // test in Postman OK
    @GetMapping("/equipment/{id}")
    public ResponseEntity<Equipment> equipmentResponseEntity(@PathVariable("id") String id) {
        Equipment equipment = this.equipmentService.getById(id);
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

}