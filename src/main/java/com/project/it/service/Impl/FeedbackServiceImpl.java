package com.project.it.service.Impl;

import com.project.it.entity.Feedback;
import com.project.it.repository.FeedbackRepository;
import com.project.it.service.FeedbackService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId).orElseThrow(() -> new RuntimeException("No feedbacks found with id: " + feedbackId));
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedbackById(Feedback feedback, Long feedbackId) {
        Feedback feedbackToUpdate = getFeedbackById(feedbackId);
        feedbackToUpdate.setFeedbackType(feedback.getFeedbackType());
        feedbackToUpdate.setDescription(feedback.getDescription());
        feedbackToUpdate.setUser(feedback.getUser());
        return feedbackRepository.save(feedbackToUpdate);
    }

    @Override
    public void deleteFeedbackById(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
