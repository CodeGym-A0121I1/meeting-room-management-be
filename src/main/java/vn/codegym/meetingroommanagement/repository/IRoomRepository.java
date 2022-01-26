package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.room.Room;

import java.util.List;

@Repository
public interface IRoomRepository extends JpaRepository<Room, String> {

    @Query(value = "select count(r.id) from Room as r")
    Integer getNumberOfRoom();
    @Query(value="select r from Room as r " +
            "join Floor as f on f.id = r.floor.id " +
            "join Area as a on a.id = r.area.id " +
            "join RoomType as rt on rt.id = r.roomType.id " +
            "where r.name like %:name% and r.floor.id = :floor " +
            "and r.area.id = :area and r.roomType.id = :roomType " +
            "and r.capacity = :capacity and r.status = :status")
    List<Room> findRoomFilter(@Param("name") String name,
                              @Param("floor") Integer floor,
                              @Param("area") Integer area,
                              @Param("roomType") Integer roomType,
                              @Param("capacity") Integer capacity ,
                              @Param("status") EStatus status);
}