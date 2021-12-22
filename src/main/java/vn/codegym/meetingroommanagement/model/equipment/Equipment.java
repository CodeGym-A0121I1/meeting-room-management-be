package vn.codegym.meetingroommanagement.model.equipment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.codegym.meetingroommanagement.model.EStatus;

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

    private double price;

    private String image;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn
    private Category category;
}