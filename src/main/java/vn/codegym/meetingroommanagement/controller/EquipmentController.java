package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.impl.EquipmentService;

import java.util.Optional;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Equipment equipment){
        equipmentService.save(equipment);
        return ResponseEntity.ok().body(equipment);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable("id") String id){
        Optional<Equipment> equipmentOptional = equipmentService.getById(id);
        return equipmentOptional.map(equipment -> new ResponseEntity<>(equipment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}