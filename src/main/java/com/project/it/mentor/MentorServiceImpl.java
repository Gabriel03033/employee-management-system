package com.project.it.mentor;

import com.project.it.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    @Override
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor getMentorById(Long mentorId) {
        return mentorRepository.findById(mentorId).orElseThrow(() -> new ResourceNotFoundException("No mentor found with id: " + mentorId));
    }

    @Override
    public Mentor saveMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor updateMentorById(Mentor mentor, Long mentorId) {
        Mentor mentorToUpdate = getMentorById(mentorId);
        mentorToUpdate.setName(mentor.getName());
        mentorToUpdate.setEmail(mentor.getEmail());
        mentorToUpdate.setPassword(mentor.getPassword());
        mentorToUpdate.setMobile(mentor.getMobile());
        mentorToUpdate.setAddress(mentor.getAddress());
        mentorToUpdate.setBirthday(mentor.getBirthday());
        mentorToUpdate.setAvailable(mentor.getAvailable());
        mentorToUpdate.setNumberOfEmployees(mentor.getNumberOfEmployees());
        return mentorRepository.save(mentorToUpdate);
    }

    @Override
    public void deleteMentorById(Long mentorId) {
        mentorRepository.deleteById(mentorId);
    }
}
