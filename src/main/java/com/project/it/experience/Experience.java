package com.project.it.experience;

import com.project.it.experience.enums.ExperienceType;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;

    private String title;

    private String organization;

    private String description;

    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return experienceId.equals(that.experienceId) &&
                title.equals(that.title) &&
                organization.equals(that.organization) &&
                description.equals(that.description) &&
                experienceType == that.experienceType &&
                startedAt.equals(that.startedAt) &&
                finishedAt.equals(that.finishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceId, title, organization, description, experienceType, startedAt, finishedAt);
    }
}
