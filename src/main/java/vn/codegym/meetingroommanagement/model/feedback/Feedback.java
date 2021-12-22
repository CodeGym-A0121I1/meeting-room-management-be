package vn.codegym.meetingroommanagement.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    private String id;

//    Ngày yêu cầu phản hồi
    private LocalDate date_request;

//    Ngày trả lời phản hồi
    private LocalDate date_response;

//    Ghi chú thêm
    private String note;

//   one to one
    private User user;

//    one to many
    private Equipment equipment;

//    one to many
    private Problem problems;


//    One to many
    private Room rooms;


}