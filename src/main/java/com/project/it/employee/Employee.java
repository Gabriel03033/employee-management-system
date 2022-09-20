package com.project.it.employee;

import com.project.it.employee.enums.Grade;
import com.project.it.employee.enums.JobType;
import com.project.it.employee.enums.Position;
import com.project.it.experience.Experience;
import com.project.it.mentor.Mentor;
import com.project.it.person.Person;
import com.project.it.studies.Studies;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "employees")
public class Employee extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "studies_id")
    private Studies studies;

    @ManyToMany
    @JoinTable(
            name = "employees_experiences",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "experience_id")
    )
    @ToString.Exclude
    private List<Experience> experiences;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) &&
                jobType == employee.jobType &&
                position == employee.position &&
                grade == employee.grade &&
                mentor.equals(employee.mentor) &&
                studies.equals(employee.studies) &&
                experiences.equals(employee.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, jobType, position, grade, mentor, studies, experiences);
    }
}
