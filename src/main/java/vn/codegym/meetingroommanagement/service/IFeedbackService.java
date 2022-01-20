package vn.codegym.meetingroommanagement.service;

import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;

@Service
public interface IFeedbackService extends IService<Feedback, String> {

    void sendEmail(String toEmail, String subject, String content);
}