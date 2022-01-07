package vn.codegym.meetingroommanagement.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.meetingroommanagement.model.feedback.Feedback;
import vn.codegym.meetingroommanagement.service.IFeedbackService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

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
        return feedback.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Feedback feedback) {
        // set time now request for Feedback
        if (feedback.getNoteRequest().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        feedback.setDateRequest(LocalDate.now());
        this.feedbackService.save(feedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody String noteResponse) {
        if (id.isEmpty() || noteResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Feedback> feedbackOptional = this.feedbackService.getById(id);

        if (feedbackOptional.isPresent()) {
            // set time now response and set noteResponse of Admin to fix Feedback
            feedbackOptional.get().setDateResponse(LocalDate.now());
            feedbackOptional.get().setNoteResponse(noteResponse);
            this.feedbackService.save(feedbackOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}