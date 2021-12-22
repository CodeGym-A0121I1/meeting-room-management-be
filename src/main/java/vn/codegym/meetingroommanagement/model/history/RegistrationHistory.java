package vn.codegym.meetingroommanagement.model.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private String id;
    private LocalDate date_start;
    private LocalDate date_end;
    private LocalTime time_start;
    private LocalTime time_end;
    private String description;
    private boolean is_cancle;
//    @ManyToOne
    private Room room;
//    @ManyToOne
    private User user;

}
