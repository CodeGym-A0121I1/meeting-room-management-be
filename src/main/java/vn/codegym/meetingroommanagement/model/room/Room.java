package vn.codegym.meetingroommanagement.model.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import javax.persistence.*;
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

    private String name;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    private String image;

    private Integer capacity;

    @ManyToOne
    @JoinColumn
    private Area area;

    @ManyToOne
    @JoinColumn
    private Floor floor;

    @ManyToOne
    @JoinColumn
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<RegistrationHistory> registrationHistoryList;

    @OneToMany(mappedBy = "room")
    private List<Equipment> equipmentList;
}