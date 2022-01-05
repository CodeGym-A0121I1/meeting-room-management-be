package vn.codegym.meetingroommanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.codegym.meetingroommanagement.model.user.Account;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.model.user.ERole;
import vn.codegym.meetingroommanagement.model.user.User;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserController_addUser {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // check if username input is null
    @Test
    public void testAddUser_username_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("");
        account.setPassword("12345");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if password input is null
    @Test
    public void testAddUser_password_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("nguyenvana");
        account.setPassword("");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if fullname input is null
    @Test
    public void testAddUser_fullname_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("nguyenvana");
        account.setPassword("12345");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if user role input is null
    @Test
    public void testAddUser_role_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("nguyenvana");
        account.setPassword("12345");
        account.setRole(null);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if department input is null
    @Test
    public void testAddUser_department_null() throws Exception {
        User user = new User();
        Account account = new Account();
        account.setUsername("");
        account.setPassword("12345");
        account.setRole(ERole.ROLE_USER);
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if username and password input is null
    @Test
    public void testAddUser_username_password_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("");
        account.setPassword("");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if username and fullname input is null
    @Test
    public void testAddUser_username_fullname_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("");
        account.setPassword("12345");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if username and user role input is null
    @Test
    public void testAddUser_username_role_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("");
        account.setPassword("12345");
        account.setRole(null);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if username and department input is null
    @Test
    public void testAddUser_username_department_null() throws Exception {
        User user = new User();
        Account account = new Account();
        account.setUsername("");
        account.setPassword("12345");
        account.setRole(ERole.ROLE_USER);
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if password and fullname input is null
    @Test
    public void testAddUser_password_fullname_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("nguyenvana");
        account.setPassword("");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if password and user role input is null
    @Test
    public void testAddUser_password_role_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("nguyenvana");
        account.setPassword("");
        account.setRole(null);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if password and department input is null
    @Test
    public void testAddUser_password_department_null() throws Exception {
        User user = new User();
        Account account = new Account();
        account.setUsername("nguyenvana");
        account.setPassword("");
        account.setRole(ERole.ROLE_USER);
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if username password and fullname input is null
    @Test
    public void testAddUser_username_password_fullname_null() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("");
        account.setPassword("");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if role and department input is null
    @Test
    public void testAddUser_role_department_null() throws Exception {
        User user = new User();
        Account account = new Account();
        account.setUsername("nguyenvana");
        account.setPassword("12345");
        account.setRole(null);
        user.setFullName("");
        user.setAccount(account);
        user.setDepartment(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if all input is null
    @Test
    public void testAddUser_all_null() throws Exception {
        User user = new User();
        user.setFullName("");
        user.setAccount(null);
        user.setDepartment(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // check if all input is valid
    @Test
    public void testAddUser_valid() throws Exception {
        User user = new User();
        Account account = new Account();
        Department department = new Department();
        account.setUsername("nguyenvana");
        account.setPassword("12345");
        account.setRole(ERole.ROLE_USER);
        department.setId(1);
        department.setName("Le tan");
        user.setFullName("Nguyen Van A");
        user.setAccount(account);
        user.setDepartment(department);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .content(this.objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
