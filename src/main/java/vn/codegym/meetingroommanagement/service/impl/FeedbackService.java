package vn.codegym.meetingroommanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;
import vn.codegym.meetingroommanagement.repository.IFeedbackRepository;
import vn.codegym.meetingroommanagement.service.IFeedbackService;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Autowired
    private MailSender mailSender;

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

    @Override
    public void sendEmail(String toEmail, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // this email address can be changed to the new one email of accepter
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);

        System.out.println("Mail sent successfully ......");
    }
}