package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codegym.meetingroommanagement.model.equipment.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select count(status) from equipment where category_id = ?1 and status = ?2", nativeQuery = true)
    Integer countByCategoryIdAndStatus(int id, String status);
}