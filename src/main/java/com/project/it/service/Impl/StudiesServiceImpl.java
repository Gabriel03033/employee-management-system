package com.project.it.service.Impl;

import com.project.it.entity.Studies;
import com.project.it.repository.StudiesRepository;
import com.project.it.service.StudiesService;
import java.util.List;

public class StudiesServiceImpl implements StudiesService {

    private final StudiesRepository studiesRepository;


    public StudiesServiceImpl(StudiesRepository studiesRepository) { this.studiesRepository = studiesRepository;}

    @Override
    public List<Studies> getAllStudies() { return studiesRepository.findAll();}

    @Override
    public Studies getStudiesById(Long studiesId) {
        return studiesRepository.findById(studiesId).orElseThrow(() -> new RuntimeException("No studies found with id: " + studiesId));}

    @Override
    public Studies saveStudies(Studies studies) {
        return studiesRepository.save(studies);
    }

    @Override
    public Studies updateStudiesById(Studies studies, Long studiesId) {
        Studies studiesToUpdate = getStudiesById(studiesId);
        studiesToUpdate.setStudiesId(studies.getStudiesId());
        studiesToUpdate.setUniversity(studies.getUniversity());
        studiesToUpdate.setFaculty(studies.getFaculty());
        studiesToUpdate.setMajor(studies.getMajor());
        return studiesRepository.save(studies);
    }

    @Override
    public void deleteStudiesById(Long studiesId) {
        studiesRepository.deleteById(studiesId);
    }
}
