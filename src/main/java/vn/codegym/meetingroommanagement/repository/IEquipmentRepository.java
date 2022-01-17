package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.meetingroommanagement.model.equipment.Equipment;

import java.util.List;

public interface IEquipmentRepository extends JpaRepository<Equipment, String> {

    List<Equipment> findAllByCategory_Id(int id_category);

    // get quantity StatusEquipment of Category
    @Query(value = "SELECT COUNT(status) FROM equipment WHERE category_id = ?1 AND status = ?2", nativeQuery = true)
    Integer countByCategory_IdAndStatus(Integer id, String status);

    List<Equipment> findAllByCategory_IdAndNameContaining(Integer id_category, String nameEquipment);

}