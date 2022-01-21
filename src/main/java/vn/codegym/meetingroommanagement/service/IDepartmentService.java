package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.user.Department;

@Service
public interface IDepartmentService extends IService<Department, Integer> {
}