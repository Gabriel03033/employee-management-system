package com.project.it.controller;

import com.project.it.entity.Experience;
import com.project.it.service.ExperienceService;
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
@RequestMapping("/experiences")
public class ExperienceRestController {

    private final ExperienceService experienceService;

    public ExperienceRestController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<Experience> getAllExperiences() {
        return experienceService.getAllExperiences();
    }

    @GetMapping("/{experienceId}")
    public Experience getExperienceById(@PathVariable Long experienceId) {
        return experienceService.getExperienceById(experienceId);
    }

    @PostMapping
    public Experience saveExperience(@RequestBody Experience experience) {
        return experienceService.saveExperience(experience);
    }

    @PutMapping("/{experienceId}")
    public Experience updateExperienceById(@RequestBody Experience experience, @PathVariable Long experienceId) {
        return experienceService.updateExperienceById(experience, experienceId);
    }

    @DeleteMapping("/{experienceId}")
    public void deleteExperienceById(@PathVariable Long experienceId) {
        experienceService.deleteExperienceById(experienceId);
    }
}
