package vn.codegym.meetingroommanagement.dto;

import lombok.Data;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Category;

@Data
public class EquipmentRequestDTO {
    private String id;

    private String name;

    private String description;

    private Double price;

    private String image;

    private EStatus status;

    private Category category;

    private RoomDTO roomDTO;
}
