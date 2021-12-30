package vn.codegym.meetingroommanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.service.impl.EquipmentService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/")
    public ResponseEntity<List<Equipment>> showList(){
        List<Equipment> equipmentList = equipmentService.getAll();
        if (equipmentList.isEmpty()){
            return new ResponseEntity<List<Equipment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Equipment>>(equipmentList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable("id") String id){
        Optional<Equipment> equipmentOptional = equipmentService.getById(id);
        return equipmentOptional.map(equipment -> new ResponseEntity<>(equipment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}