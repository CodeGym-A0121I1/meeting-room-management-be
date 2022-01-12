package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {
    @Query("select r " +
            "from RegistrationHistory r inner join r.room inner join r.room.roomType inner join r.user " +
            "where r.isCancel=false " +
            "and r.dateStart>=?1 and r.dateEnd<=?2")
    List<RegistrationHistory> statisticByTime(LocalDate startTime, LocalDate endTime);

    @Query("select r " +
            "from RegistrationHistory r inner join r.room inner join r.room.roomType inner join r.user " +
            "where r.room.roomType.name like concat('%',:roomType,'%') " +
            "and r.isCancel=false " +
            "and r.room.name like concat('%',:roomName,'%')" +
            "and ((substring(r.dateStart,6, 2) like concat('%',:month,'%') and substring(r.dateStart,1, 4) like concat('%',:year,'%')) " +
            "or (substring(r.dateEnd,6, 2) like concat('%',:month,'%') and substring(r.dateEnd,1, 4) like concat('%',:year,'%')))")
    List<RegistrationHistory> statisticByRoom(@Param("roomType") String roomType, @Param("roomName") String roomName, @Param("month") String month, @Param("year") String year);
}
