package com.project.it.service;

import com.project.it.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    Feedback getFeedbacksById(Long feedbackId);
    Feedback saveFeedbacks(Feedback feedback);
    Feedback updateFeedbacksById(Feedback feedback, Long feedbackId);
    void deleteFeedbacksById(Long feedbackId);
}
