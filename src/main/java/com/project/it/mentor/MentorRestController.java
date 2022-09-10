package com.project.it.mentor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mentors")
public class MentorRestController {

    private final MentorService mentorService;

    public MentorRestController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public ResponseEntity<List<Mentor>> getAllMentors() {
        return new ResponseEntity<>(mentorService.getAllMentors(), HttpStatus.OK);
    }

    @GetMapping("/{mentorId}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable Long mentorId) {
        return new ResponseEntity<>(mentorService.getMentorById(mentorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mentor> saveMentor(@RequestBody Mentor mentor) {
        return new ResponseEntity<>(mentorService.saveMentor(mentor), HttpStatus.CREATED);
    }

    @PutMapping("/{mentorId}")
    public ResponseEntity<Mentor> updateMentorById(@RequestBody Mentor mentor, @PathVariable Long mentorId) {
        return new ResponseEntity<>(mentorService.updateMentorById(mentor, mentorId), HttpStatus.OK);
    }

    @DeleteMapping("/{mentorId}")
    public ResponseEntity<String> deleteMentorById(@PathVariable Long mentorId) {
        mentorService.deleteMentorById(mentorId);
        return new ResponseEntity<>("Mentor deleted sucessfully", HttpStatus.NO_CONTENT);
    }
}
