package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;
import vn.codegym.meetingroommanagement.repository.IFeedbackRepository;
import vn.codegym.meetingroommanagement.service.IFeedbackService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAll() {
        return this.feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> getById(String id) {
        return this.feedbackRepository.findById(id);
    }

    @Override
    public Feedback save(Feedback feedback) {
        return this.feedbackRepository.save(feedback);
    }

    @Override
    public void delete(Feedback entity) {
    }

    @Override
    public void deleteById(String id) {
        this.feedbackRepository.deleteById(id);
    }
}