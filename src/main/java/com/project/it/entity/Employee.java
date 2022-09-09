package com.project.it.entity;

import com.project.it.enums.EmployeeType;
import com.project.it.enums.Grade;
import com.project.it.enums.Position;
import java.time.LocalDate;
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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private String address;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
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
    private List<Experience> experiences;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                email.equals(employee.email) &&
                password.equals(employee.password) &&
                mobile.equals(employee.mobile) &&
                address.equals(employee.address) &&
                birthday.equals(employee.birthday) &&
                employeeType == employee.employeeType &&
                position == employee.position &&
                grade == employee.grade &&
                mentor.equals(employee.mentor) &&
                studies.equals(employee.studies) &&
                experiences.equals(employee.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, email, password, mobile, address, birthday, employeeType, position, grade, mentor, studies, experiences);
    }
}
