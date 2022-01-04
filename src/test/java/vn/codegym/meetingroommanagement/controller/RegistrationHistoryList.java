package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationHistoryList {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegistrationHistoryController registrationHistoryController;

    //list has size > 0
    @Test
    public void testGetAllRegistrationHistory_200() throws Exception {
        ResponseEntity<List<RegistrationHistory>> listResponseEntity = this.registrationHistoryController.getAll();
        List<RegistrationHistory> registrationHistoryList = listResponseEntity.getBody();
        Assertions.assertEquals("RH09", registrationHistoryList.get(0).getId());
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/registration-histories")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    //list has size = 0
    @Test
    public void testGetAllRegistrationHistory_400() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/registration-histories")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
}
