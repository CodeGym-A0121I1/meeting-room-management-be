package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.repository.IDepartmentRepository;
import vn.codegym.meetingroommanagement.service.IDepartmentService;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository iDepartmentRepository;

    @Override
    public List<Department> getAll() {
        return iDepartmentRepository.findAll();
    }

    @Override
    public Optional<Department> getById(Integer key) {
        return iDepartmentRepository.findById(key);
    }

    @Override
    public Department save(Department entity) {
        return iDepartmentRepository.save(entity);
    }

    @Override
    public void delete(Department entity) {
        iDepartmentRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer key) {
        iDepartmentRepository.deleteById(key);
    }
}