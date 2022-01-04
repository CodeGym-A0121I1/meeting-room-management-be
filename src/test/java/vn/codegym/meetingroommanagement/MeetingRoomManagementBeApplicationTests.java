package vn.codegym.meetingroommanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.codegym.meetingroommanagement.model.jwt.JwtRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MeetingRoomManagementBeApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

//    username is right, password is right
//    username: trongvt, password:123
    @Test
    public void login_1() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/accounts/login")
                        .content(this.objectMapper.writeValueAsString(new JwtRequest("trongvt","123")))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account.username").value("trongvt"))
                .andExpect(jsonPath("$.jwtToken").exists())
                .andExpect(jsonPath("$.status").doesNotExist());
    }
    @Test
//    username is wrong, password is right
//    username: admin, password:123
    public void login_2() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/accounts/login")
                        .content(this.objectMapper.writeValueAsString(new JwtRequest("admin","123")))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwtToken").doesNotExist())
                .andExpect(jsonPath("$.status").value("Not found user: admin"));
    }

    //    username is right, password is wrong
    //    username: trongvt, password:1234
@Test
    public void login_3() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/accounts/login")
                        .content(this.objectMapper.writeValueAsString(new JwtRequest("trongvt","1234")))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwtToken").doesNotExist())
                .andExpect(jsonPath("$.status").value("Password was wrong !"));
    }
    //    username is null, password is null
    //    username: , password:
    @Test
    public void login_4() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/accounts/login")
                        .content(this.objectMapper.writeValueAsString(new JwtRequest("","")))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwtToken").doesNotExist())
                .andExpect(jsonPath("$.status").value("Not found user: "));
    }

}