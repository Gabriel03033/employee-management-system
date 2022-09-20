package com.project.it.feedback;

import com.project.it.user.User;
import com.project.it.feedback.enums.FeedbackType;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return feedbackId.equals(feedback.feedbackId) &&
                feedbackType == feedback.feedbackType &&
                description.equals(feedback.description) &&
                user.equals(feedback.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, feedbackType, description, user);
    }
}
