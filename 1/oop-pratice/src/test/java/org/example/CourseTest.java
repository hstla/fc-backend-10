package org.example;


import org.example.grade.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CourseTest {
    @DisplayName("과목(코스)을 생산한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new Course("OOP",3,"A+"))
                .doesNotThrowAnyException();
    }
}
