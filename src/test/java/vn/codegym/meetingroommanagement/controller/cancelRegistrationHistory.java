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
public class cancelRegistrationHistory {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testIdNull() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/registration-histories/cancel/{id}","null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testIdNotInDB() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/registration-histories/cancel/{id}","RH01"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testIdEqualBlannk() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/registration-histories/cancel/{id}",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void testIdInDB() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/registration-histories/cancel/{id}","RH09"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
