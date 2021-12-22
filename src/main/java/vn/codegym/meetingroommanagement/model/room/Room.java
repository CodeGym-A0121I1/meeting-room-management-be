package vn.codegym.meetingroommanagement.model.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private Integer roomId;
    private String roomName;
    private Integer roomStatus;
    private String image;
    private Integer capacity;
    @ManyToOne
    @JoinColumn(name = "areaId",referencedColumnName = "areaId",nullable = false)
    private Area area;
    @ManyToOne
    @JoinColumn(name = "floorId",referencedColumnName = "floorId",nullable = false)
    private Floor floor;
    @ManyToOne
    @JoinColumn(name = "roomTypeId",referencedColumnName = "roomTypeId",nullable = false)
    private RoomType roomType;

}