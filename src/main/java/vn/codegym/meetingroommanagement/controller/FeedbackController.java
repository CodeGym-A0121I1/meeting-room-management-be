package vn.codegym.meetingroommanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;
import vn.codegym.meetingroommanagement.service.IFeedbackService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final IFeedbackService feedbackService;

    public FeedbackController(IFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("")
    public ResponseEntity<List<Feedback>> getAll() {
        List<Feedback> feedbackList = this.feedbackService.getAll();
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getById(@PathVariable("id") String id) {
        Optional<Feedback> feedback = this.feedbackService.getById(id);
        return feedback.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> create(@RequestBody Feedback feedback) {
        // set time now request for Feedback
        if (feedback.getNoteRequest().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        feedback.setDateRequest(LocalDate.now());
        feedbackService.save(feedback);
        try {
            this.feedbackService.sendEmail("trungtrongcr21@gmail.com", "NEW FEEDBACK", feedback.toStringRequest());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") String id, @RequestBody String noteResponse) {
        if (id.isEmpty() || noteResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Feedback feedback = this.feedbackService.getById(id).orElse(null);


        if (feedback == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // set time now response and set noteResponse of Admin to fix Feedback
        feedback.setDateResponse(LocalDate.now());
        feedback.setNoteResponse(noteResponse);
        feedback.setStatus(true);
        this.feedbackService.save(feedback);
        try {
            this.feedbackService.sendEmail(feedback.getUser().getEmail(), "REPLY FEEDBACK", feedback.toStringResponse());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}