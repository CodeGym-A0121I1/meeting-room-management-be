package vn.codegym.meetingroommanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.codegym.meetingroommanagement.model.EStatus;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EquipmentController_update {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // [item] = null
    @Test
    public void update_1() throws Exception {

        EStatus status = null;

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/equipments/{id}", "EQUIP0010")
                        .content(this.objectMapper.writeValueAsString(status))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [item] =  rỗng ("")
    @Test
    public void update_2() throws Exception {

        String status = "";

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/equipments/{id}", "EQUIP0010")
                        .content(this.objectMapper.writeValueAsString(status))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    // [item] sai format ( variable status must depend on EStatus.class )
    @Test
    public void update_3() throws Exception {

        String status = "wrong format";
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/equipments/{id}", "EQUIP0010")
                        .content(this.objectMapper.writeValueAsString(status))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // [item] hợp lệ
    @Test
    public void update_4() throws Exception {

        EStatus status = EStatus.FIXING;
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/equipments/{id}", "EQUIP0010")
                        .content(this.objectMapper.writeValueAsString(status))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
