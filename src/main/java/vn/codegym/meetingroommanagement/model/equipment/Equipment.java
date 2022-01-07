package vn.codegym.meetingroommanagement.model.equipment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.room.Room;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotBlank(message = "Price is mandatory")
    
    private Double price;

    private String image;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Room room;
}