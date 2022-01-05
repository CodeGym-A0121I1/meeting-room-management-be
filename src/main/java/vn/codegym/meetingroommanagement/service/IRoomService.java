package vn.codegym.meetingroommanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.room.Room;

@Service
public interface IRoomService extends IService<Room,String>{
    Page<Room> getAll(Pageable pageable);
}