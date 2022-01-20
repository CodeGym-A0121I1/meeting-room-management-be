package vn.codegym.meetingroommanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;

import java.util.List;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, String> {
    List<Feedback> findByOrderByIdDesc();
}