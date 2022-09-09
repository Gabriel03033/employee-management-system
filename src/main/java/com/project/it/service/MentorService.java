package com.project.it.service;

import com.project.it.entity.Mentor;
import java.util.List;

public interface MentorService {
    List<Mentor> getAllMentors();

    Mentor getMentorById(Long mentorId);

    Mentor saveMentor(Mentor mentor);

    Mentor updateMentorById(Mentor mentor, Long mentorId);

    void deleteMentorById(Long mentorId);
}
