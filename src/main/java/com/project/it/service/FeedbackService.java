package com.project.it.service;

import com.project.it.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    Feedback getFeedbackById(Long feedbackId);

    Feedback saveFeedback(Feedback feedback);

    Feedback updateFeedbackById(Feedback feedback, Long feedbackId);

    void deleteFeedbackById(Long feedbackId);
}
