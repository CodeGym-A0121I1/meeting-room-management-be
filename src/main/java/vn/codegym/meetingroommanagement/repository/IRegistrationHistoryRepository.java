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
    @Query("select s.user.fullName, s.room.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.dateStart>=?1 and s.dateEnd<=?2")
    List<?> roomStatistic(LocalDate startTime, LocalDate endTime);

    @Query("select s.user.fullName, s.room.name, s.room.roomType.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.room.status, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.room.roomType.name=?1 or s.room.name=?2  " +
            "or (substring(s.dateStart,6, 2)=?3 or substring(s.dateEnd,6, 2)=?3) or (substring(s.dateStart,1, 4)=?4 or substring(s.dateEnd,1, 4)=?4)")
    List<?> roomStatistic(String roomType, String roomName, String month, String year);

    @Query("select count(s.room.name) " +
            "from RegistrationHistory s inner join s.room " +
            "where s.isCancel=false " +
            "group by s.room.name having s.room.name=?1")
    int roomCountStatistic(String roomName);

    //query search list
    @Query("select r "+
            "from RegistrationHistory as r inner join Room as o on r.room.id = o.id "+
            "where o.name like '%:roomName%'"
//            "and r.dateStart between ':dateStart' and ':dateEnd'"+
//            "and r.dateEnd between ':dateStart' and ':dateEnd'"+
//            "and o.status like '%:status%'"
    )
    List<RegistrationHistory> REGISTRATION_HISTORY_LIST(@Param("roomName") String roomName
//                                                        @Param("dateStart") Date dateStart,
//                                                        @Param("dateEnd") Date dateEnd,
//                                                        @Param("status") String status
    );
}
