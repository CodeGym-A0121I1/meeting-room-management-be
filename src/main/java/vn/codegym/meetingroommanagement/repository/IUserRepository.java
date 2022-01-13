package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.user.ERole;
import vn.codegym.meetingroommanagement.model.user.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where ( u.account.username like %:name% ) and (:role is null or u.account.role= :role )")
    List<User> search(String name, ERole role);
}