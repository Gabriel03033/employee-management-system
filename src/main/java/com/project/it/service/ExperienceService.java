package com.project.it.service;

import com.project.it.entity.Experience;
import java.util.List;

public interface ExperienceService {
    List<Experience> getAllExperiences();
    Experience getExperiencesById(Long experienceId);
    Experience saveExperiences(Experience experience);
    Experience updateExperiencesById(Experience experience, Long experienceId);
    void deleteExperiencesById(Long experienceId);
}
