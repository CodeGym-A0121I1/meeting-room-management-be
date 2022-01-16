package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.meetingroommanagement.model.room.RoomType;

public interface IRoomTypeRepository extends JpaRepository<RoomType, Integer> {
}