package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping
    private ResponseEntity<List<Equipment>> getAllEquipments(){

        return ResponseEntity.ok(equipmentService.getAll());
    }

    @PostMapping
    private ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment){
        equipment.setStatus(EStatus.AVAILABLE);

        return ResponseEntity.ok(equipmentService.save(equipment));
    }
}