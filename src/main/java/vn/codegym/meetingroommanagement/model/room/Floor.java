package vn.codegym.meetingroommanagement.model.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Floor {
    @Id
    private Integer floorId;
    private String floorName;

    @OneToMany(mappedBy = "floor",cascade = CascadeType.ALL)
    private List<Room> rooms;
}