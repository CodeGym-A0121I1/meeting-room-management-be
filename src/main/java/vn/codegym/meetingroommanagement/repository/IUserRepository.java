package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.user.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
}