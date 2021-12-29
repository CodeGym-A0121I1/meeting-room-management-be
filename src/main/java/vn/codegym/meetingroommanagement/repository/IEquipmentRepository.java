package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

public interface IEquipmentRepository extends JpaRepository<Equipment, String> {

    List<Equipment> findAllByCategory_Id(int id_category);

    // get quantity StatusEquipment of Category
    @Query(value = "select count(status) from equipment where category_id = ?1 and status = ?2", nativeQuery = true)
    Integer countByCategory_IdAndStatus(int id, String status);

    List<Equipment> findAllByCategory_IdAndNameContaining(int id_category, String nameEquipment);

}