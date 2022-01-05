package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EquipmentController_getAllEquipmentByCategoryId {

    @Autowired
    private MockMvc mockMvc;

    // comment the method getAllCategory() in EquipmentController.java before running this class

    // [idCategory] = null
    @Test
    public void getAllEquipmentByCategoryId_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/equipments/categories/{idCategory}", (String) null))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [idCategory] = rỗng ("")
    @Test
    public void getAllEquipmentByCategoryId_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/equipments/categories/{idCategory}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [idCategory] không tồn tại trong DB
    @Test
    public void getAllEquipmentByCategoryId_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/equipments/categories/{idCategory}", 2222))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [idCategory] tồn tại trong DB & Trả về list có size = 0
    @Test
    public void getAllEquipmentByCategoryId_4() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/equipments/categories/{idCategory}", 1))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [idCategory] tồn tại trong DB & Trả về list có size > 0
    @Test
    public void getAllEquipmentByCategoryId_5() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/equipments/categories/{idCategory}", 2))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
