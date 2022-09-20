package com.project.it.employee;

import com.project.it.employee.enums.Grade;
import com.project.it.employee.enums.JobType;
import com.project.it.employee.enums.Position;
import com.project.it.experience.Experience;
import com.project.it.person.PersonDto;
import java.util.List;
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
public class EmployeeDto extends PersonDto {

    private Long employeeId;

    private JobType jobType;

    private Position position;

    private Grade grade;

    private Long mentorId;

    private Long studiesId;

    private List<Experience> experiences;
}
