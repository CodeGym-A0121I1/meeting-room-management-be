package vn.codegym.meetingroommanagement.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    private String note;

    @ManyToOne
    private User user;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Problem problem;

    @ManyToOne
    private Room room;


}