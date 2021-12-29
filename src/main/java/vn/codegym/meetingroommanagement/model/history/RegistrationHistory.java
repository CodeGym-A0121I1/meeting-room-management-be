package vn.codegym.meetingroommanagement.model.history;

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
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistrationHistory {

    @Id
    @GeneratedValue(generator = "historyIdGen")
    @GenericGenerator(
            name = "historyIdGen",
            parameters = @Parameter(name = "prefix", value = "RH"),
            strategy = "vn.codegym.meetingroommanagement.utils.IdGenerator"
    )
    private String id;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private LocalTime timeStart;

    private LocalTime timeEnd;

    private String description;

    private boolean isCancel;

    @ManyToOne
    @JoinColumn
    private Room room;

    @ManyToOne
    @JoinColumn
    private User user;
}