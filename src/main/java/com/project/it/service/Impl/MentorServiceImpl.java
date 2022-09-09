package com.project.it.service.Impl;

import com.project.it.entity.Mentor;
import com.project.it.repository.MentorRepository;
import com.project.it.service.MentorService;
import java.util.List;

public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor getMentorById(Long mentorId) {
        return mentorRepository.findById(mentorId).orElseThrow(() -> new RuntimeException("No mentors found with id: " + mentorId));
    }

    @Override
    public Mentor saveMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor updateMentorById(Mentor mentor, Long mentorId) {
        Mentor mentorToUpdate = getMentorById(mentorId);
        mentorToUpdate.setFirstName(mentor.getFirstName());
        mentorToUpdate.setLastName(mentor.getFirstName());
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
