package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.codegym.meetingroommanagement.model.room.Room;



@SpringBootTest
@AutoConfigureMockMvc
public class RoomRestController_listRoom {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RoomController roomController;
    // trương hợp list trả về có size bằng 0
    // test OK
    @Test
    public void testGetListRoom_1(){
        ResponseEntity<Page<Room>> responseEntity = this.roomController.getAll(PageRequest.of(0,2));
        Assertions.assertEquals(404,responseEntity.getStatusCodeValue());
    }
    // trường hợp list trả về có size > 0
    // test OK
    @Test
    public void testGetListRoom_2(){
        ResponseEntity<Page<Room>> responseEntity = this.roomController.getAll(PageRequest.of(0,2));
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
    }
    // trường họp list trả về 2 page và có 3 giá trị
    // test OK
    @Test
    public void testGetListRoom_3(){
        ResponseEntity<Page<Room>> responseEntity = this.roomController.getAll(PageRequest.of(0,2));
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2,responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(3,responseEntity.getBody().getTotalElements());
    }
}
