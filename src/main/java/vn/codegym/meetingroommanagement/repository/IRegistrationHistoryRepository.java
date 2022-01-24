package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {
    @Query("select r " +
            "from RegistrationHistory r " +
            "where r.isCancel=false " +
            "and r.dateStart>=?1 and r.dateEnd<=?2")
    List<RegistrationHistory> statisticByTime(LocalDate startTime, LocalDate endTime);

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

    @Query("select count(s.room.id) " +
            "from RegistrationHistory s inner join s.room " +
            "where s.isCancel=false " +
            "group by s.room.id having s.room.id=?1")
    Integer roomCountStatisticById(String roomid);

    //query search list

    @Query(value = "select r from RegistrationHistory as r" +
            " inner join " +
            "Room as o on r.room.id=o.id " +
            " where" +
            " r.isCancel = true and" +
            " o.name like %:roomName% and" +
            " o.status = :status" +
            " and o.roomType.id = :roomType and r.dateStart between :start and :end and r.dateEnd between :start and :end")
    List<RegistrationHistory> REGISTRATION_HISTORY_LIST(@Param("roomName") String roomName,
                                                        @Param("start") LocalDate dateStart,
                                                        @Param("end") LocalDate dateEnd,
                                                        @Param("status") EStatus status,
                                                        @Param("roomType") Integer roomType
    );

    @Query(value = "select r from RegistrationHistory as r" +
            " inner join " +
            "Room as o on r.room.id=o.id " +
            " where" +
            " r.isCancel = true and" +
            " o.name like %:roomName% and" +
            " o.status = :status" +
            " and r.dateStart between :start and :end and r.dateEnd between :start and :end")
    List<RegistrationHistory> searchRegistrationHistoryByNotRoomType(@Param("roomName") String roomName,
                                                                     @Param("start") LocalDate dateStart,
                                                                     @Param("end") LocalDate dateEnd,
                                                                     @Param("status") EStatus status
    );


    @Query(value = "select r from RegistrationHistory as r where r.isCancel = false ")
    List<RegistrationHistory> countRegistrationHistoryByIsCancel();

    @Query("select  s from RegistrationHistory s " +
            "where s.room.id=?1")
    List<RegistrationHistory> findAllRegistrationHistoryByRoomId(String id);

    @Query(value = "SELECT * FROM registration_history \n" +
            "INNER JOIN user ON registration_history.user_id= user.id\n" +
            "INNER JOIN room ON registration_history.room_id= room.id\n" +
            "INNER JOIN room_type ON room.room_type_id= room_type.id\n" +
            "WHERE is_cancel=0 AND room_type.name LIKE %:roomType% AND room.name LIKE %:roomName% \n" +
            "AND ((date_start > :timeStart AND date_end < :timeEnd )\n" +
            "OR (date_start < :timeStart AND date_end BETWEEN :timeStart AND :timeEnd)\n" +
            "OR (date_start BETWEEN :timeStart AND :timeEnd AND date_end > :timeEnd)\n" +
            "OR (date_start < :timeStart AND date_end > :timeEnd))", nativeQuery = true)
    List<RegistrationHistory> statisticByRoom(@Param("roomType") String roomType, @Param("roomName") String roomName, @Param("timeStart") LocalDate timeStart, @Param("timeEnd") LocalDate timeEnd);
}