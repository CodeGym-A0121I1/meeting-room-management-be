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

@Test
    public void testGetInformationRoom_null() throws Exception{
    this.mockMvc.perform(
            MockMvcRequestBuilders
                    .get("/api/rooms/{id}","null"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
}

    @Test
    public void testGetInfoStudent_1() throws Exception {
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
