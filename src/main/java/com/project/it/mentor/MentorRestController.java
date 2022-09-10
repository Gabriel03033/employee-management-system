package com.project.it.mentor;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentors")
public class MentorRestController {

    private final MentorService mentorService;

    public MentorRestController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<Mentor> getAllMentors() { return mentorService.getAllMentors(); }

    @GetMapping("/{mentorId}")
    public Mentor getMentorById(@PathVariable Long mentorId) {
        return mentorService.getMentorById(mentorId);
    }

    @PostMapping
    public Mentor saveMentor(@RequestBody Mentor mentor) {
        return mentorService.saveMentor(mentor);
    }

    @PutMapping("/{mentorId}")
    public Mentor updateMentorById(@RequestBody Mentor mentor, @PathVariable Long mentorId) {
        return mentorService.updateMentorById(mentor, mentorId);
    }

    @DeleteMapping("/{mentorId}")
    public void deleteMentorById(@PathVariable Long mentorId) {
        mentorService.deleteMentorById(mentorId);
    }
}
