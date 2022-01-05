package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;
import vn.codegym.meetingroommanagement.model.equipment.Category;

import java.util.List;

@SpringBootTest
public class EquipmentController_getAllCategory {

    @Autowired
    private EquipmentController equipmentController;

    // Trường hợp Trả về list có size = 0
    @Test
    public void getAllCategory_1() {
        ResponseEntity<List<Category>> responseEntity
                = this.equipmentController.getAllCategory();
        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
    }

    // Trường hợp Trả về list có size > 0
    @Test
    public void getAllCategory_2() {
        ResponseEntity<List<Category>> responseEntity
                = this.equipmentController.getAllCategory();
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
