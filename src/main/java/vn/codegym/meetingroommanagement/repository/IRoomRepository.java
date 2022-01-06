package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.room.Room;

@Repository
public interface IRoomRepository extends JpaRepository<Room, String> {
    // bỏ ràng buộc khoá ngoại với table room
    @Modifying
    @Query("update Equipment e set e.room.id = null where e.room.id like ?1")
    void setRoomNull(String id);
}