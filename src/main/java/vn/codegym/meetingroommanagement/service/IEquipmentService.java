package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

@Service
public interface IEquipmentService extends IService<Equipment, String> {
}