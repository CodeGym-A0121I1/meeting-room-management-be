package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.user.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT `account_username` FROM `user`", nativeQuery = true)
    List<String> getAllUsername();
}