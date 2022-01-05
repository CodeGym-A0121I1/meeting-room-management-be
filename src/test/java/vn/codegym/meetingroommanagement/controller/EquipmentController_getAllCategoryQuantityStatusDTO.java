package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import vn.codegym.meetingroommanagement.dto.CategoryQuantityStatusDTO;

import java.util.List;

@SpringBootTest
public class EquipmentController_getAllCategoryQuantityStatusDTO {

    @Autowired
    private EquipmentController equipmentController;

    // Trường hợp Trả về list có size = 0
    @Test
    public void getAllCategoryQuantityStatusDTO_1() {
        ResponseEntity<List<CategoryQuantityStatusDTO>> responseEntity
                = this.equipmentController.getAllCategoryQuantityStatusDTO();
        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
    }

    // Trường hợp Trả về list có size > 0
    @Test
    public void getAllCategoryQuantityStatusDTO_2() {
        ResponseEntity<List<CategoryQuantityStatusDTO>> responseEntity
                = this.equipmentController.getAllCategoryQuantityStatusDTO();
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals("Máy chiếu", responseEntity.getBody().get(0).getNameCategory());
    }
}
