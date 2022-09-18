package com.project.it.experience;

import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceRestController {

    private final ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {
        return new ResponseEntity<>(experienceService.getAllExperiences(), HttpStatus.OK);
    }

    @GetMapping("/{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long experienceId) {
        return new ResponseEntity<>(experienceService.getExperienceById(experienceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Experience> saveExperience(@RequestBody Experience experience) {
        return new ResponseEntity<>(experienceService.saveExperience(experience), HttpStatus.CREATED);
    }

    @PutMapping("/{experienceId}")
    public ResponseEntity<Experience> updateExperienceById(@RequestBody Experience experience, @PathVariable Long experienceId) {
        return new ResponseEntity<>(experienceService.updateExperienceById(experience, experienceId), HttpStatus.OK);
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<String> deleteExperienceById(@PathVariable Long experienceId) {
        experienceService.deleteExperienceById(experienceId);
        return new ResponseEntity<>("Experience deleted successfully", HttpStatus.NO_CONTENT);
    }
}
