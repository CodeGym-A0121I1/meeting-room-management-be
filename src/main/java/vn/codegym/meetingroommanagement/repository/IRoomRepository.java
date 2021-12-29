package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.room.Room;

@Repository
public interface IRoomRepository extends JpaRepository<Room, String> {
}