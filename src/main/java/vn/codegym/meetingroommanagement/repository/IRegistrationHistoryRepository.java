package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

    @Query("select s " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.room.roomType.name like concat('%',:roomType,'%') " +
            "and s.room.name like concat('%',:roomName,'%')" +
            "and ((substring(s.dateStart,6, 2) like concat('%',:month,'%') and substring(s.dateStart,1, 4) like concat('%',:year,'%')) " +
            "or (substring(s.dateStart,6, 2) like concat('%',:month,'%') and substring(s.dateStart,1, 4) like concat('%',:year,'%')))")
    List<RegistrationHistory> roomStatistic(@Param("roomType") String roomType, @Param("roomName") String roomName, @Param("month") String month, @Param("year") String year);
}
