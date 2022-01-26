package vn.codegym.meetingroommanagement.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Category;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.ICategoryService;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final ICategoryService categoryService;

    private final IEquipmentService equipmentService;

    private final ModelMapper modelMapper;

    public EquipmentController(ICategoryService categoryService, IEquipmentService equipmentService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.equipmentService = equipmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory(@RequestParam(value = "room") String roomID) {

        List<Category> categoryList = this.categoryService.getAll();

        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryDTOList.add(modelMapper.map(category, CategoryDTO.class));
        }

        if (!roomID.equals("")) {
            for (CategoryDTO categoryDTO : categoryDTOList) {
                categoryDTO.setEquipmentList(categoryDTO.getEquipmentList().stream().filter(equipment ->
                        equipment.getStatus() == EStatus.AVAILABLE || equipment.getRoom().getId().equals(roomID)
                ).collect(Collectors.toList()));
            }
        } else {
            for (CategoryDTO categoryDTO : categoryDTOList) {
                categoryDTO.setEquipmentList(categoryDTO.getEquipmentList().stream().filter(equipment ->
                        equipment.getStatus() == EStatus.AVAILABLE
                ).collect(Collectors.toList()));
            }
        }
        categoryDTOList = categoryDTOList.stream().filter(categoryDTO -> !categoryDTO.getEquipmentList().isEmpty()).collect(Collectors.toList());

        return categoryDTOList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @GetMapping("/categories/{idCategory}")
    public ResponseEntity<List<Equipment>> getAllEquipmentByCategoryId(@PathVariable("idCategory") Integer idCategory) {
        List<Equipment> equipmentList = this.equipmentService.getAllByCategoryId(idCategory);
        if (equipmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        Optional<Equipment> equipment = this.equipmentService.getById(id);
        if (equipment.isPresent()) {
            this.equipmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody String status) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Equipment> equipmentOptional = this.equipmentService.getById(id);
        if (equipmentOptional.isPresent()) {
            equipmentOptional.get().setStatus(EStatus.valueOf(status));
            this.equipmentService.save(equipmentOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

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

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody EquipmentDTO equipmentDTO) {
        equipmentDTO.setStatus(EStatus.AVAILABLE);
        Equipment equipment = modelMapper.map(equipmentDTO, Equipment.class);
        equipmentService.save(equipment);
        return ResponseEntity.ok().body(equipment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentRequestDTO> getById(@PathVariable("id") String id) {
        Optional<Equipment> equipmentOptional = equipmentService.getById(id);
        if (equipmentOptional.isPresent()) {
            EquipmentRequestDTO equipmentRequestDTO = modelMapper.map(equipmentOptional.get(), EquipmentRequestDTO.class);
            equipmentRequestDTO.setRoomDTO(modelMapper.map(equipmentOptional.get(), RoomDTO.class));
            return new ResponseEntity<>(equipmentRequestDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchEquipments(@RequestParam("name") String name, @RequestParam("category") Integer categoryId) {
        List<CategoryDTO> result = new ArrayList<>();
        if (categoryId == 0) {
            result = categoryService.getAllEquipmentByName(name);
        } else {
            CategoryDTO categoryDTO = categoryService.getAllEquipmentByCategoryAndName(name, categoryId);

            if (categoryDTO != null) {
                result.add(categoryDTO);
            }
        }
        return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

}