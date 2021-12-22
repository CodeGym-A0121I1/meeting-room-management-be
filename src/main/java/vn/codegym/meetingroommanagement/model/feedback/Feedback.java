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
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    Ngày yêu cầu phản hồi
    private Date date_request;

//    Ngày trả lời phản hồi
    private Date date_response;

//    Ghi chú thêm
    private String note;

//   one to one
    private List<User> user;

//    one to one
    private List<Equipment> equipment;

//    one to one
    private List<Problem> problems;

    private List<Room> rooms;

    public Feedback() {
    }

    public Feedback(int id, Date date_request, Date date_response, String note, List<User> user, List<Equipment> equipment, List<Problem> problems, List<Room> rooms) {
        this.id = id;
        this.date_request = date_request;
        this.date_response = date_response;
        this.note = note;
        this.user = user;
        this.equipment = equipment;
        this.problems = problems;
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_request() {
        return date_request;
    }

    public void setDate_request(Date date_request) {
        this.date_request = date_request;
    }

    public Date getDate_response() {
        return date_response;
    }

    public void setDate_response(Date date_response) {
        this.date_response = date_response;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}