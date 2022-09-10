package com.project.it.controller;

import com.project.it.entity.Feedback;
import com.project.it.service.FeedbackService;
import java.util.List;
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
    public List<Feedback> getAllFeedbacks(Long feedbackId) {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{feedbackId}")
    public Feedback getFeedbackById(@PathVariable Long feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }

    @PostMapping
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @PutMapping("/{feedbackId}")
    public Feedback updateFeedbackById(@RequestBody Feedback feedback, @PathVariable Long feedbackId) {
        return feedbackService.updateFeedbackById(feedback, feedbackId);
    }

    @DeleteMapping("/{feedbackId}")
    public void deleteFeedbackById(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedbackById(feedbackId);
    }
}
