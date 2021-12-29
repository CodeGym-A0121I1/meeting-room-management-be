package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

@Repository
public interface IEquipmentRepository extends JpaRepository<Equipment, String> {
}