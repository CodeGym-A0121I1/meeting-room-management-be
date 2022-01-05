package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.util.List;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {

    @Query("select s.user.fullName, s.room.name, s.room.roomType.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.room.status, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.room.roomType.name=?1  " +
            "and (substring(s.dateStart,6, 2)=?2 or substring(s.dateEnd,6, 2)=?2) and (substring(s.dateStart,1, 4)=?3 or substring(s.dateEnd,1, 4)=?3)")
    List<?> roomTypeStatistic(String roomType, String month, String year);

    @Query("select s.user.fullName, s.room.name, s.room.roomType.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.room.status, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.room.name=?1 " +
            "and (substring(s.dateStart,6, 2)=?2 or substring(s.dateEnd,6, 2)=?2) and (substring(s.dateStart,1, 4)=?3 or substring(s.dateEnd,1, 4)=?3)")
    List<?> roomNameStatistic(String roomName, String month, String year);

    @Query("select s.user.fullName, s.room.name, s.room.roomType.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.room.status, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where (substring(s.dateStart,6, 2)=?1 or substring(s.dateEnd,6, 2)=?1) and (substring(s.dateStart,1, 4)=?2 or substring(s.dateEnd,1, 4)=?2)")
    List<?> roomDateStatistic(String month, String year);

    @Query("select s.user.fullName, s.room.name, s.room.roomType.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.room.status, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.room.roomType.name=?1")
    List<?> roomTypeStatistic(String roomType);

    @Query("select s.user.fullName, s.room.name, s.room.roomType.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.room.status, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.user " +
            "where s.room.name=?1")
    List<?> roomNameStatistic(String roomName);

    @Query("select count(s.room.name) " +
            "from RegistrationHistory s inner join s.room " +
            "where s.isCancel=false " +
            "group by s.room.name having s.room.name=?1")
    int roomCountStatistic(String roomName);
}
