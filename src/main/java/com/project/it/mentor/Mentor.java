package com.project.it.mentor;

import com.project.it.person.Person;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "mentors")
public class Mentor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorId;

    private Boolean available;

    private Integer numberOfEmployees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return mentorId.equals(mentor.mentorId) &&
                available.equals(mentor.available) &&
                numberOfEmployees.equals(mentor.numberOfEmployees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mentorId, available, numberOfEmployees);
    }
}
