package vn.codegym.meetingroommanagement.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(generator = "feedbackIdGen")
    @GenericGenerator(
            name = "feedbackIdGen",
            parameters = @Parameter(name = "prefix", value = "FB"),
            strategy = "vn.codegym.meetingroommanagement.utils.IdGenerator"
    )
    private String id;

    private LocalDate dateRequest;

    private LocalDate dateResponse;

    private String noteRequest;

    private String noteResponse;

    private boolean status;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Problem problem;

    @ManyToOne
    @JoinColumn
    private Room room;

    public String toStringRequest() {
        return "ID người phản hồi: " + user.getId() +
                "\nTên phòng: " + room.getName() +
                "\nNgày gửi: " + dateRequest +
                "\nNội dung: " + noteRequest +
                "\nTrạng thái: Chưa xử lý";
    }

    public String toStringResponse() {
        return "Người xử lý: Admin" +
                "\nTên phòng: " + room.getName() +
                "\nNgày gửi phản hồi: " + dateRequest +
                "\nNội dung phản hồi: " + noteRequest +
                "\nNgày xử lý: " + dateResponse +
                "\nNội dung xử lý: " + noteResponse +
                "\nTrạng thái: Đã xử lý";
    }

}