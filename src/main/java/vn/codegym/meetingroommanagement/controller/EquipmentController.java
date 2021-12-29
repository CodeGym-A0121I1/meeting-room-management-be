package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.impl.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping()
    public ResponseEntity<List<Equipment>> listEquipments(){
        List<Equipment> equipments = equipmentService.getAll();
        if (equipments.isEmpty()){
            return new ResponseEntity<List<Equipment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Equipment>>(equipments, HttpStatus.OK);
    }

}