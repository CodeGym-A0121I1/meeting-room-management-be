package vn.codegym.meetingroommanagement.dto;

import lombok.Data;

@Data
public class CategoryQuantityStatusDTO {
    private Integer idCategory;
    private String nameCategory;
    private int fixing;
    private int available;
    private int using;
}