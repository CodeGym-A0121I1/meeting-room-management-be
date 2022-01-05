package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class EquipmentController_TestGetList {

    @Autowired
    private MockMvc mockMvc;

    /*
        List have elements
     */
    @Test
    public void getAllEquipments_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/equipments")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    /*
        Empty list
     */
    @Test
    public void getAllEquipments_2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/equipments")
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}