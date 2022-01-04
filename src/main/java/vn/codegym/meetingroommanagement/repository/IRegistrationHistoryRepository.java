package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.util.List;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {
    @Query("select s.user.fullName, s.room.name, s.dateStart, s.dateEnd, s.timeStart, s.timeEnd, s.isCancel " +
            "from RegistrationHistory s inner join s.room inner join s.room.roomType inner join s.user " +
            "where s.room.roomType.name=?1 and s.room.name=?2")
    List<?> roomStatistic(String roomType, String roomName, int month, int year);

    @Query("select count(s.room.name) " +
            "from RegistrationHistory s inner join s.room " +
            "where s.isCancel=false " +
            "group by s.room.name having s.room.name=?1")
    int roomStatistic(String roomName);
}
