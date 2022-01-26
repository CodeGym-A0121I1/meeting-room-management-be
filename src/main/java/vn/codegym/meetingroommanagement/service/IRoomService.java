package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.room.Room;

import java.util.List;

@Service
public interface IRoomService extends IService<Room, String> {

    String getNameForImage();

    boolean deleteRoomById(String id);

    List<Room> findRoomFilter(String name, Integer floor, Integer area, Integer roomType, Integer capacity , EStatus status);
}