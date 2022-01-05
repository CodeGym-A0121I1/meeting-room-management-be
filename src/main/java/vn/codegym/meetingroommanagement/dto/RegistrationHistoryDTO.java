package vn.codegym.meetingroommanagement.dto;

import lombok.Builder;
import lombok.Data;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.model.room.RoomType;
import vn.codegym.meetingroommanagement.model.user.User;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class RegistrationHistoryDTO {
    private String id;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private LocalTime timeStart;

    private LocalTime timeEnd;

    private String description;

    private boolean isCancel;

    private Room room;

    private User user;

    private Integer  countRoomName = 0;

}
