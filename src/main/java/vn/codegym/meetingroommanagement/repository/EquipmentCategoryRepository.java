package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.meetingroommanagement.model.equipment.Category;

public interface EquipmentCategoryRepository extends JpaRepository<Category, Integer> {
    // check id lặp
//    Boolean existsById(int id);

    @Query(value = "select count(status) from equipment where category_id = ?1 and status = ?2", nativeQuery = true)
    Integer countByCategory_IdAndStatus(int id, String status);
}

