package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.meetingroommanagement.model.equipment.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}