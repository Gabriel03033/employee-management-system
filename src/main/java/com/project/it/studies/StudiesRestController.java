package com.project.it.studies;

import java.util.List;
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
@RequestMapping("/api/studies")
public class StudiesRestController {

    private final StudiesService studiesService;

    public StudiesRestController(StudiesService studiesService) {
        this.studiesService = studiesService;
    }

    @GetMapping
    public ResponseEntity<List<Studies>> getAllStudies() {
        return new ResponseEntity<>(studiesService.getAllStudies(), HttpStatus.OK);
    }

    @GetMapping("/{studiesId}")
    public ResponseEntity<Studies> getStudiesById(@PathVariable Long studiesId) {
        return new ResponseEntity<>(studiesService.getStudiesById(studiesId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Studies> saveStudies(@RequestBody Studies studies) {
        return new ResponseEntity<>(studiesService.saveStudies(studies), HttpStatus.CREATED);
    }

    @PutMapping("/{studiesId}")
    public ResponseEntity<Studies> updateStudiesById(@RequestBody Studies studies, @PathVariable Long studiesId) {
        return new ResponseEntity<>(studiesService.updateStudiesById(studies, studiesId), HttpStatus.OK);
    }

    @DeleteMapping("/{studiesId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long studiesId) {
        studiesService.deleteStudiesById(studiesId);
        return new ResponseEntity<>("Studies deleted successfully", HttpStatus.NO_CONTENT);
    }

}
