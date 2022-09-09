package com.project.it.service.Impl;

import com.project.it.entity.Experience;
import com.project.it.repository.ExperienceRepository;
import com.project.it.service.ExperienceService;
import java.util.List;

public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId).orElseThrow(() -> new RuntimeException("No experiences found with id: " + experienceId));
    }

    @Override
    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public Experience updateExperienceById(Experience experience, Long experienceId) {
        Experience experienceToUpdate = getExperienceById(experienceId);
        experienceToUpdate.setTitle(experience.getTitle());
        experienceToUpdate.setOrganization(experience.getOrganization());
        experienceToUpdate.setDescription(experience.getDescription());
        experienceToUpdate.setExperienceType(experience.getExperienceType());
        experienceToUpdate.setStartedAt(experience.getStartedAt());
        experienceToUpdate.setFinishedAt(experience.getFinishedAt());
        return experienceRepository.save(experienceToUpdate);
    }

    @Override
    public void deleteExperienceById(Long experienceId) {

    }
}
