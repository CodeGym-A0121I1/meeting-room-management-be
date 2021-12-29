package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.equipment.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService extends IService<Category, Integer> {

    @Override
    List<Category> getAll();

    @Override
    Optional<Category> getById(Integer key);
}