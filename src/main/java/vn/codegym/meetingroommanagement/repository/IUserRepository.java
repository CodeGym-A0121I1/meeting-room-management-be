package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.user.Department;
import vn.codegym.meetingroommanagement.model.user.ERole;
import vn.codegym.meetingroommanagement.model.user.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where ( :username is null or u.account.username like %:username% ) and (:role is null or u.account.role= :role) and (:fullName is null or u.fullName like %:fullName%) and (:departmentName is null or u.department.id= :departmentName)")
    List<User> search(String username, ERole role, String fullName,Integer departmentName);
}