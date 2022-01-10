package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

public interface IEquipmentRepository extends JpaRepository<Equipment, String> {

}