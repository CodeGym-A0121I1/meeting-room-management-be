package vn.codegym.meetingroommanagement.service;

import vn.codegym.meetingroommanagement.model.room.RoomType;

public interface IRoomTypeService {
    Iterable<RoomType> findAll();
}
