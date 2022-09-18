package com.project.it.utils;

import com.project.it.employee.Employee;
import com.project.it.employee.enums.Grade;
import com.project.it.employee.enums.JobType;
import com.project.it.employee.enums.Position;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeUtils {

    public static Employee getEmployeeGeorgeBacalu() {
        return Employee.builder()
                .employeeId(1L)
                .name("George Bacalu")
                .email("georgebacalu@email.com")
                .password("georgebacalu")
                .mobile("0000000001")
                .address("employee_address1")
                .birthday(LocalDate.of(2001, 3, 8))
                .jobType(JobType.FULL_TIME)
                .position(Position.BACKEND)
                .grade(Grade.JUNIOR)
                .mentor(MentorUtils.getMentorStefanPetrescu())
                .studies(StudiesUtils.getStudiesAutomatics())
                .experiences(new ArrayList<>())
                .build();
    }

    public static Employee getEmployeeGabrielFaur() {
        return Employee.builder()
                .employeeId(2L)
                .name("Gabriel Faur")
                .email("gabrielfaur@email.com")
                .password("gabrielfaur")
                .mobile("0000000002")
                .address("employee_address2")
                .birthday(LocalDate.of(2003, 1, 20))
                .jobType(JobType.FULL_TIME)
                .position(Position.BACKEND)
                .grade(Grade.JUNIOR)
                .mentor(MentorUtils.getMentorStefanPetrescu())
                .studies(StudiesUtils.getStudiesAutomatics())
                .experiences(new ArrayList<>())
                .build();
    }
}
