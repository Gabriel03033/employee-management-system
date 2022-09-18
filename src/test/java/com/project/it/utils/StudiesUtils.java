package com.project.it.utils;

import com.project.it.studies.Studies;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudiesUtils {

    public static Studies getStudiesAutomatics() {
        return Studies.builder()
                .studiesId(1L)
                .university("University of Transylvania")
                .faculty("Faculty of Electrical Engineering and Computer Science")
                .major("Automatic and Applied Computer Science")
                .build();
    }
}
