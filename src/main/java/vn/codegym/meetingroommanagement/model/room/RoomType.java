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
public class RoomType {
    @Id
    private Integer roomTypeId;
    private String roomTypeName;
    @OneToMany(mappedBy = "roomType",cascade = CascadeType.ALL)
    private List<Room> rooms;
}