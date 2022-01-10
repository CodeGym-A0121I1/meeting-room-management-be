package vn.codegym.meetingroommanagement.model.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.room.Room;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipment {

    @Id
    @GeneratedValue(generator = "equipmentIdGen")
    @GenericGenerator(
            name = "equipmentIdGen",
            parameters = @Parameter(name = "prefix", value = "EQUIP"),
            strategy = "vn.codegym.meetingroommanagement.utils.IdGenerator"
    )
    private String id;

    private String name;

    private String description;

    private Double price;

    private String image;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Room room;
}