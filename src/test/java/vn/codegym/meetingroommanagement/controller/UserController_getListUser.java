//package vn.codegym.meetingroommanagement.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.ResponseEntity;
//import vn.codegym.meetingroommanagement.model.user.User;
//
//@SpringBootTest
//public class UserController_getListUser {
//
//    @Autowired
//    private UserController userController;
//
//    //Trường hợp trả về list có size=0
//    @Test
//    public void getListUser_5() {
//        ResponseEntity<Page<User>> responseEntity
//                = this.userController.getAllUser(PageRequest.of(0, 2));
//
//        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
//    }
//
//    //Trường hợp trả về list có size>0
//    @Test
//    public void getListStudent_6() {
//
//        ResponseEntity<Page<User>> responseEntity
//                = this.userController.getAllUser(PageRequest.of(0, 2));
//
//        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
//        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
//        Assertions.assertEquals(2, responseEntity.getBody().getTotalElements());
//        Assertions.assertEquals("thang",
//                responseEntity.getBody().getContent().get(0).getFullName());
//    }
//}
