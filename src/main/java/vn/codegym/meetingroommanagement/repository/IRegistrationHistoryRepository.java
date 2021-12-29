package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.history.RegistrationHistory;

@Repository
public interface IRegistrationHistoryRepository extends JpaRepository<RegistrationHistory, String> {
}