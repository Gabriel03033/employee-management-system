package com.project.it.studies;

import java.util.List;

public interface StudiesService {
    List<Studies> getAllStudies();

    Studies getStudiesById(Long studiesId);

    Studies saveStudies(Studies studies);

    Studies updateStudiesById(Studies studies, Long studiesId);

    void deleteStudiesById(Long studiesId);
}
