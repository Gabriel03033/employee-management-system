package com.project.it.service;

import com.project.it.entity.Mentor;
import java.util.List;

public interface MentorService {
    List<Mentor> getAllMentors();
    Mentor getMentorsById(Long mentorId);
    Mentor saveMentors(Mentor mentor);
    Mentor updateMentorsById(Mentor mentor, Long mentorId);
    void deleteMentorsById(Long mentorId);
}
