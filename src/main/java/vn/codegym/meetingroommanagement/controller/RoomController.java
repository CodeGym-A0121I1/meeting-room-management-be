package vn.codegym.meetingroommanagement.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.dto.RoomDTO;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;
import vn.codegym.meetingroommanagement.model.room.Area;
import vn.codegym.meetingroommanagement.model.room.Floor;
import vn.codegym.meetingroommanagement.model.room.Room;
import vn.codegym.meetingroommanagement.model.room.RoomType;
import vn.codegym.meetingroommanagement.service.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final IRoomService roomService;

    private final IEquipmentService equipmentService;

    private final IAreaService areaService;

    private final IFloorService floorService;

    private final IRoomTypeService roomTypeService;

    private final ModelMapper modelMapper;
    

    public RoomController(IRoomService roomService,
                          IEquipmentService equipmentService,
                          IAreaService areaService,
                          IRoomTypeService roomTypeService,
                          IFloorService floorService,
                          ModelMapper modelMapper,
                          IRegistrationHistoryService registrationHistoryService) {
        this.roomService = roomService;
        this.equipmentService = equipmentService;
        this.areaService = areaService;
        this.floorService = floorService;
        this.roomTypeService = roomTypeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoom() {
        List<Room> roomList = roomService.getAll();

        return roomList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @GetMapping("dto")
    public ResponseEntity<List<RoomDTO>> getAllRoomDTO() {
        List<Room> roomList = this.roomService.getAll();
        if (roomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomList) {
            roomDTOList.add(modelMapper.map(room, RoomDTO.class));
        }
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
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
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") String id) {
        Optional<Room> room = roomService.getById(id);
        return room.map(r -> {
            if (roomService.deleteRoomById(id)) {
                return new ResponseEntity<>(room.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(room.get(), HttpStatus.METHOD_NOT_ALLOWED);
            }
        }).orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping("/areas")
    public ResponseEntity<Area> createArea(@RequestBody Area area) {

        return ResponseEntity.ok(areaService.save(area));
    }

    @GetMapping("/floors")
    public ResponseEntity<List<Floor>> getAllFloors() {

        return ResponseEntity.ok(floorService.getAll());
    }

    @GetMapping("/areas")
    public ResponseEntity<List<Area>> getAllAreas() {

        return ResponseEntity.ok(areaService.getAll());
    }

    @GetMapping("/searchRoom")
    public ResponseEntity<List<Room>> getRoomSearch(@RequestParam(value = "name",required = false) String name,
                                                    @RequestParam(value = "floor",required = false) Integer floor,
                                                    @RequestParam(value = "area",required = false) Integer area,
                                                    @RequestParam(value = "roomType",required = false) Integer roomType,
                                                    @RequestParam(value = "capacity",required = false) Integer capacity,
                                                    @RequestParam(value = "status",required = false) EStatus status) {
        List<Room> rooms = roomService.findRoomFilter(name,floor,area,roomType,capacity,status);
        if(rooms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/roomTypes")
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {

        return ResponseEntity.ok(roomTypeService.getAll());
    }

    @GetMapping("/image/new-name")
    public ResponseEntity<String> getNameImage() {

        return ResponseEntity.ok(roomService.getNameForImage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String field = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(field, errorMessage);
                }
        );
        return errors;
    }
}