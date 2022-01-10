package vn.codegym.meetingroommanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Area;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.service.IAreaService;
import vn.codegym.meetingroommanagement.service.IEquipmentService;
import vn.codegym.meetingroommanagement.service.IRoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final IRoomService roomService;

    private final IEquipmentService equipmentService;

    private final IAreaService areaService;

    public RoomController(IRoomService roomService, IEquipmentService equipmentService, IAreaService areaService) {
        this.roomService = roomService;
        this.equipmentService = equipmentService;
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoom() {
        List<Room> roomList = roomService.getAll();

        return roomList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        room.setStatus(EStatus.AVAILABLE);

        return new ResponseEntity<>(roomService.save(room), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);

        return room.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        Optional<Room> roomOptional = roomService.getById(room.getId());

        return roomOptional.map(r -> {
                    List<Equipment> equipmentList = new ArrayList<>();

                    r.getEquipmentList().forEach(equipment -> equipmentService.getById(equipment.getId()).ifPresent(equipmentList::add));

                    equipmentList.forEach(equipment -> {
                        equipment.setRoom(null);
                        equipment.setStatus(EStatus.AVAILABLE);
                        equipmentService.save(equipment);
                    });

                    return new ResponseEntity<>(roomService.save(room), HttpStatus.OK);
                })
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);

        return room.map(r -> {
            roomService.deleteById(id);

            return ResponseEntity.ok("Delete Successful");
        }).orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping("/area")
    public ResponseEntity<Area> createArea(@RequestBody Area area) {

        return ResponseEntity.ok(areaService.save(area));
    }
}