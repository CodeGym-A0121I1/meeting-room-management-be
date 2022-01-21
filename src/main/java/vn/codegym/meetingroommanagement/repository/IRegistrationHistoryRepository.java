package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {
    @Query("select r " +
            "from RegistrationHistory r " +
            "where r.isCancel=false " +
            "and r.dateStart>=?1 and r.dateEnd<=?2")
    List<RegistrationHistory> statisticByTime(LocalDate startTime, LocalDate endTime);

    @Query(value="select * from registration_history \n" +
            "inner join user on registration_history.user_id= user.id\n" +
            "inner join room on registration_history.room_id= room.id\n" +
            "inner join room_type on room.room_type_id= room_type.id\n" +
            "where is_cancel=0 and room_type.name like %:roomType% and room.name like %:roomName% \n" +
            "and ((date_start > :timeStart and date_end < :timeEnd )\n" +
            "or (date_start < :timeStart and date_end between :timeStart and :timeEnd)\n" +
            "or (date_start between :timeStart and :timeEnd and date_end > :timeEnd)\n" +
            "or (date_start < :timeStart and date_end > :timeEnd))",nativeQuery = true)
    List<RegistrationHistory> statisticByRoom(@Param("roomType") String roomType, @Param("roomName") String roomName, @Param("timeStart") LocalDate timeStart, @Param("timeEnd") LocalDate timeEnd);
}
