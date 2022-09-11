package com.project.it.feedback;

import com.project.it.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId).orElseThrow(() -> new ResourceNotFoundException("No feedback found with id: " + feedbackId));
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
