package com.project.it.studies;

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
@RequestMapping("/studies")
public class StudiesRestController {

    private final StudiesService studiesService;

    public StudiesRestController(StudiesService studiesService) {
        this.studiesService = studiesService;
    }

    @GetMapping
    public List<Studies> getAllStudies() {
        return studiesService.getAllStudies();
    }

    @GetMapping("/{studiesId}")
    public Studies getStudiesById(@PathVariable Long studiesId) {
        return studiesService.getStudiesById(studiesId);
    }

    @PostMapping
    public Studies saveStudies(@RequestBody Studies studies) {
        return studiesService.saveStudies(studies);
    }

    @PutMapping("/{studiesId}")
    public Studies updateStudiesById(@RequestBody Studies studies, @PathVariable Long studiesId) {
        return studiesService.updateStudiesById(studies, studiesId);
    }

    @DeleteMapping("/{studiesId}")
    public void deleteUserById(@PathVariable Long studiesId) {
        studiesService.deleteStudiesById(studiesId);
    }

}
