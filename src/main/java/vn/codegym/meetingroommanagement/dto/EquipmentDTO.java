package vn.codegym.meetingroommanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.codegym.meetingroommanagement.model.EStatus;
import vn.codegym.meetingroommanagement.model.equipment.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
    private String id;

    private String name;

    private String description;

    private Double price;

    private String image;

    private EStatus status;

    private Category category;
}