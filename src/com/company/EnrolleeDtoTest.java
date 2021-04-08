package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrolleeDtoTest {

    @Test
    void compareTo() {
        EnrolleeDto enrolleeDto1 = new EnrolleeDto("A", "B", "C", 2, "D");
        EnrolleeDto enrolleeDto2 = new EnrolleeDto("A", "B", "C", 2, "D");
        EnrolleeDto enrolleeDto3 = new EnrolleeDto("A", "C", "D", 2, "D");
        EnrolleeDto enrolleeDto4 = new EnrolleeDto("A", "C", "B", 2, "D");
        assertEquals(enrolleeDto1.compareTo(enrolleeDto2), 0);
        assertTrue(enrolleeDto1.compareTo(enrolleeDto3) < 0);
        assertTrue(enrolleeDto1.compareTo(enrolleeDto4) > 0);
    }
}