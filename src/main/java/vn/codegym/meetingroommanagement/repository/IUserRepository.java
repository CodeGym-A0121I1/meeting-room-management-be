package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.user.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    @Modifying
    @Query(value = "DELETE FROM user WHERE user.id = ?1", nativeQuery = true)
    void deleteUserById(String id);

}