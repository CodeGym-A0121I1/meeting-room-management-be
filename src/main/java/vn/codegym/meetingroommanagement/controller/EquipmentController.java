package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.IEquipmentService;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    @Autowired
    private IEquipmentService equipmentService;


    @PostMapping("/create")
    public ResponseEntity<Equipment> create(@RequestBody Equipment equipment){
        Equipment newEquipment = equipmentService.save(equipment);
        return new ResponseEntity<>(newEquipment, HttpStatus.CREATED);
    }
}