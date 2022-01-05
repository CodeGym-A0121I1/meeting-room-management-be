package vn.codegym.meetingroommanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomRestController_deleteRoom {
    @Autowired
    private MockMvc mockMvc;
    // trương hợp id không có trong database trả về status 4xx
    @Test
    public void testDeleteRoom_byId_1() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{id}","R009"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    // trương hợp id bằng rỗng trả về status 4xx
    @Test
    public void testDeleteRoom_byId_2() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{id}",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    // trường hơp id có trong database và xoá thành công
    @Test
    public void testDeleteRoom_byId_3() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{id}", "R001"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
