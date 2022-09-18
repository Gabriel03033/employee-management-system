package com.project.it.experience;

import com.project.it.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId).orElseThrow(() -> new ResourceNotFoundException("No experience found with id: " + experienceId));
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
