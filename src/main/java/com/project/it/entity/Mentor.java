package com.project.it.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "mentors")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private String address;
    private LocalDate birthday;
    private Boolean available;
    private Integer numberOfEmployees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return mentorId.equals(mentor.mentorId) &&
                firstName.equals(mentor.firstName) &&
                lastName.equals(mentor.lastName) &&
                email.equals(mentor.email) &&
                password.equals(mentor.password) &&
                mobile.equals(mentor.mobile) &&
                address.equals(mentor.address) &&
                birthday.equals(mentor.birthday) &&
                available.equals(mentor.available) &&
                numberOfEmployees.equals(mentor.numberOfEmployees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mentorId, firstName, lastName, email, password, mobile, address, birthday, available, numberOfEmployees);
    }
}
