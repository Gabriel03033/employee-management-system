package com.project.it.studies;

import com.project.it.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudiesServiceImpl implements StudiesService {

    private final StudiesRepository studiesRepository;

    @Override
    public List<Studies> getAllStudies() {
        return studiesRepository.findAll();
    }

    @Override
    public Studies getStudiesById(Long studiesId) {
        return studiesRepository.findById(studiesId).orElseThrow(() -> new ResourceNotFoundException("No studies found with id: " + studiesId));
    }

    @Override
    public Studies saveStudies(Studies studies) {
        return studiesRepository.save(studies);
    }

    @Override
    public Studies updateStudiesById(Studies studies, Long studiesId) {
        Studies studiesToUpdate = getStudiesById(studiesId);
        studiesToUpdate.setUniversity(studies.getUniversity());
        studiesToUpdate.setFaculty(studies.getFaculty());
        studiesToUpdate.setMajor(studies.getMajor());
        return studiesRepository.save(studiesToUpdate);
    }

    @Override
    public void deleteStudiesById(Long studiesId) {
        studiesRepository.deleteById(studiesId);
    }
}
