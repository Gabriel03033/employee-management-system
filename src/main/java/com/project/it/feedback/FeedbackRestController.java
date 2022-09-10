package com.project.it.feedback;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackRestController {

    private final FeedbackService feedbackService;

    public FeedbackRestController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks(Long feedbackId) {
        return new ResponseEntity<>(feedbackService.getAllFeedbacks(), HttpStatus.OK);
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        return new ResponseEntity<>(feedbackService.getFeedbackById(feedbackId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackService.saveFeedback(feedback), HttpStatus.CREATED);
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedbackById(@RequestBody Feedback feedback, @PathVariable Long feedbackId) {
        return new ResponseEntity<>(feedbackService.updateFeedbackById(feedback, feedbackId), HttpStatus.OK);
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedbackById(feedbackId);
        return new ResponseEntity<>("Feedback deleted successfully", HttpStatus.NO_CONTENT);
    }
}
