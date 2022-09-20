package com.project.it.studies;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "studies")
public class Studies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studiesId;

    private String university;

    private String faculty;

    private String major;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studies studies = (Studies) o;
        return studiesId.equals(studies.studiesId) && university.equals(studies.university) && faculty.equals(studies.faculty) && major.equals(studies.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studiesId, university, faculty, major);
    }
}
