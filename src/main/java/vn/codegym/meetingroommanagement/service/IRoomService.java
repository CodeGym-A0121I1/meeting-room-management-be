package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Room;

@Service
public interface IRoomService extends IService<Room, String> {

    public String getNameForImage();
    public boolean deleteRoomById(String id);
}