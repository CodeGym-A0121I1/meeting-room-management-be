package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@WebMvcTest(RoomController.class)
class RoomController_CreateRoom {

    @Autowired
    private MockMvc mockMvc;

    @Autowired


    /*
        Valid inputs
     */
    @Test
    void createRoom_1() {
//        mockMvc.perform()
    }

      /*
        Empty input: Tên phòng họp
        Message: Field must not be empty
     */

      /*
        Empty input: Sức chứa
        Message: Field must not be empty
     */

      /*
        Invalid input: Tên phòng họp
     */

      /*
        Invalid input: Sức chứa
        Message: Please enter a number
     */
}