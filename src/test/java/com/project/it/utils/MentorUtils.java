package com.project.it.utils;

import com.project.it.mentor.Mentor;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MentorUtils {

    public static Mentor getMentorStefanPetrescu() {
        return Mentor.builder()
                .mentorId(1L)
                .name("Stefan Petrescu")
                .email("stefanpetrescu@email.com")
                .password("stefanpetrescu")
                .mobile("1000000001")
                .address("mentor_address1")
                .birthday(LocalDate.of(1997, 1, 1))
                .available(true)
                .numberOfEmployees(5)
                .build();
    }
}
