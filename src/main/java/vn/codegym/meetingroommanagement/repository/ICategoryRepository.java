package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.meetingroommanagement.model.equipment.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT COUNT(status) FROM equipment WHERE category_id = ?1 AND status = ?2", nativeQuery = true)
    Integer countByCategory_IdAndStatus(int id, String status);

}