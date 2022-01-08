package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.history.RegistrationDTO;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

import java.sql.Date;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {
    @Modifying
    @Query(value = "select user.id as id, user.full_name as fullName, room.name as roomName, room_type.name as roomType, registration_history.time_start as timeStart, registration_history.time_end as timeEnd, registration_history.date_start as dateStart, registration_history.date_end as dateEnd, room.status as status\n" +
            "from `registration_history`\n" +
            "left join room on registration_history.room_id = room.id\n" +
            "left join user on registration_history.user_id = user.id\n" +
            " join room_type on room.room_type_id = room_type.id\n" +
            " where user.full_name like ' %:fullName%' and dateStart between ?2 and ?3", nativeQuery = true)
    Page<RegistrationDTO> statisticalByName(@Param("fullName") String name, Date dateStart, Date dateEnd, Pageable pageable);
}