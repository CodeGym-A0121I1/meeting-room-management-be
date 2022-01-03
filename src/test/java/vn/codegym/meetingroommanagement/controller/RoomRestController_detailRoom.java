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
public class RoomRestController_detailRoom {
@Autowired
    private MockMvc mockMvc;
// trương hợp id = null trả về status 4xx
@Test
    public void testGetInformationRoom_byId_null() throws Exception{
    this.mockMvc.perform(
            MockMvcRequestBuilders
                    .get("/api/rooms/{id}","null"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
}
    // trương hợp id khong có trong database trả về status 4xx
    @Test
    public void testGetInformationRoom_byId_not_in_Database() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/rooms/{id}","room46"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    // trương hợp id bằng rỗng trả về status 4xx
    @Test
    public void testGetInformationRoom_byId_equal_blank() throws Exception{
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/rooms/{id}",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    // trường hơp id có trong database trả về 2xx
    @Test
    public void testGetInformationRoom_byId() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/rooms/{id}", "room1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("hội thảo của madam uyen 66"))
                .andExpect(jsonPath("$.id").value("room1"))
                .andExpect(jsonPath("$.status").value("USING"))
                .andExpect(jsonPath("$.area.id").value("1"))
        ;
    }
}
