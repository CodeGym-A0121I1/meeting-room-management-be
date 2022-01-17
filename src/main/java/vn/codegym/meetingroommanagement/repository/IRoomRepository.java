package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.room.Room;

@Repository
public interface IRoomRepository extends JpaRepository<Room, String> {

    @Query(value = "select count(r.id) from Room as r")
    Integer getNumberOfRoom();
}