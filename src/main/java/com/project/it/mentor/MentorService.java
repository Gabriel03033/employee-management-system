package com.project.it.mentor;

import java.util.List;

public interface MentorService {
    List<Mentor> getAllMentors();

    Mentor getMentorById(Long mentorId);

    Mentor saveMentor(Mentor mentor);

    Mentor updateMentorById(Mentor mentor, Long mentorId);

    void deleteMentorById(Long mentorId);
}
