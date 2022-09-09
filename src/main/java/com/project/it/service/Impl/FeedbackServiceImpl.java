package com.project.it.service.Impl;

import com.project.it.entity.Feedback;
import com.project.it.repository.FeedbackRepository;
import com.project.it.service.FeedbackService;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) { this.feedbackRepository = feedbackRepository;}


    @Override
    public List<Feedback> getAllFeedbacks() { return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbacksById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId).orElseThrow(() -> new RuntimeException("No feedbacks found with id: " + feedbackId));}

    @Override
    public Feedback saveFeedbacks(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedbacksById(Feedback feedback, Long feedbackId) {
        Feedback feedbackToUpdate = getFeedbacksById(feedbackId);
        feedbackToUpdate.setFeedbackType(feedback.getFeedbackType());
        feedbackToUpdate.setDescription(feedback.getDescription());
        feedbackToUpdate.setUser(feedback.getUser());
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedbacksById(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
