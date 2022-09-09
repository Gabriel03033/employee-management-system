package com.project.it.service;

import com.project.it.entity.Experience;
import java.util.List;

public interface ExperienceService {
    List<Experience> getAllExperiences();

    Experience getExperienceById(Long experienceId);

    Experience saveExperience(Experience experience);

    Experience updateExperienceById(Experience experience, Long experienceId);

    void deleteExperienceById(Long experienceId);
}
