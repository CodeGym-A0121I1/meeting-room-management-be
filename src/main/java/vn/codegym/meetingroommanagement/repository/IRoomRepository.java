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
            "where (:name is null or r.name like %:name%) and (:floor is null or r.floor.id = :floor) " +
            "and (:area is null or r.area.id = :area) and (:roomType is null or r.roomType.id = :roomType )" +
            "and (:capacity is null or r.capacity = :capacity) and (:status is null or r.status = :status)")
    List<Room> findRoomFilter(@Param("name") String name,
                              @Param("floor") Integer floor,
                              @Param("area") Integer area,
                              @Param("roomType") Integer roomType,
                              @Param("capacity") Integer capacity,
                              @Param("status") EStatus status);
}