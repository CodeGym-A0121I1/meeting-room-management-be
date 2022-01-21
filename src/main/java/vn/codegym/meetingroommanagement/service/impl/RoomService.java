package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.repository.IRoomRepository;
import vn.codegym.meetingroommanagement.service.IEquipmentService;
import vn.codegym.meetingroommanagement.service.IRegistrationHistoryService;
import vn.codegym.meetingroommanagement.service.IRoomService;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RoomService implements IRoomService {

    private final IRoomRepository roomRepository;

    private final IEquipmentService equipmentService;

    private final IRegistrationHistoryService registrationHistoryService;
    public RoomService(IRoomRepository roomRepository, IEquipmentService equipmentService, IRegistrationHistoryService registrationHistoryService) {
        this.roomRepository = roomRepository;
        this.equipmentService = equipmentService;
        this.registrationHistoryService = registrationHistoryService;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(String key) {
        return roomRepository.findById(key);
    }

    @Override
    public Room save(Room entity) {

        List<Equipment> equipmentList = new ArrayList<>();
        entity.getEquipmentList().forEach(equipment -> equipmentService.getById(equipment.getId()).ifPresent(equipmentList::add));

        Room room = roomRepository.save(entity);

        equipmentList.forEach(equipment -> {
            equipment.setRoom(room);
            equipment.setStatus(EStatus.USING);
            equipmentService.save(equipment);
        });

        return room;
    }

    @Override
    public void deleteById(String key) {

    }

    @Override
    public String getNameForImage() {
        return "imageRoom" + roomRepository.getNumberOfRoom();
    }

    @Override
    public boolean deleteRoomById(String key) {
        AtomicBoolean checkStatus = new AtomicBoolean(false);
        Optional<Room> roomOptional = getById(key);
        roomOptional.ifPresent(room -> {
            boolean checkDayAndTime = true;
            List<RegistrationHistory> registrationHistoryList = new ArrayList<>();
            if(registrationHistoryService.findAllRegistrationHistoryByRoomId(key).isEmpty()) {
                checkDayAndTime = true;
            }else {
               registrationHistoryList = registrationHistoryService.findAllRegistrationHistoryByRoomId(key);
                for (RegistrationHistory registrationHistory:registrationHistoryList){
                    if((LocalDate.now().compareTo(registrationHistory.getDateStart()) > 0 && LocalDate.now().compareTo(registrationHistory.getDateEnd()) < 0)
                            || (LocalDate.now().compareTo(registrationHistory.getDateStart()) < 0)) {
                        checkDayAndTime = false;
                        break;
                    }
                }
            }
            if (checkDayAndTime == true){
                List<Equipment> equipmentList = new ArrayList<>();
                room.getEquipmentList().forEach(equipment -> equipmentService.getById(equipment.getId()).ifPresent(equipmentList::add));

                equipmentList.forEach(equipment -> {
                    equipment.setRoom(null);
                    equipment.setStatus(EStatus.AVAILABLE);
                    equipmentService.save(equipment);
                });
                registrationHistoryList.forEach(registrationHistory -> {
                    registrationHistory.setRoom(null);
                    registrationHistoryService.save(registrationHistory);
                });
                roomRepository.deleteById(key);
                checkStatus.set(true);
            }else {
                checkStatus.set(false);
            }
        });
        return checkStatus.get();
    }
}