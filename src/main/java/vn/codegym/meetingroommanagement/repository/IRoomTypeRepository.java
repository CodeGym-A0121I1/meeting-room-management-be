package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.room.RoomType;

@Repository
public interface IRoomTypeRepository extends JpaRepository<RoomType, Integer> {
}