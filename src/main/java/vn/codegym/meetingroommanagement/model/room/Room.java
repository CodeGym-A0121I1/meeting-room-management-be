package vn.codegym.meetingroommanagement.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(generator = "roomIdGen")
    @GenericGenerator(
            name = "roomIdGen",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "ROOM"),
            strategy = "vn.codegym.meetingroommanagement.utils.IdGenerator"
    )
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    private String image;

    @NotNull(message = "Capacity is mandatory")
    @Min(value = 0, message = "Capacity must be bigger than 0")
    private Integer capacity;

    @ManyToOne
    @JoinColumn
    @NotNull(message = "Area is mandatory")
    private Area area;

    @ManyToOne
    @JoinColumn
    @NotNull(message = "Floor is mandatory")
    private Floor floor;

    @ManyToOne
    @JoinColumn
    @NotNull(message = "Room Type is mandatory")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    List<RegistrationHistory> registrationHistoryList;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    List<Feedback> feedbackList;

    @OneToMany(mappedBy = "room")
    private List<Equipment> equipmentList;
}