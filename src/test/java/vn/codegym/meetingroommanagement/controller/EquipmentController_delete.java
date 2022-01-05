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
public class EquipmentController_delete {

    @Autowired
    private MockMvc mockMvc;

    // [id] = null
    @Test
    public void delete_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/equipments/{id}", (String) null))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [id] = rỗng ("")
    @Test
    public void delete_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/equipments/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [id] không tồn tại trong DB
    @Test
    public void delete_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/equipments/{id}", "3333"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [id] tồn tại trong DB
    @Test
    public void delete_4() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/equipments/{id}", "EQUIP0009"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
