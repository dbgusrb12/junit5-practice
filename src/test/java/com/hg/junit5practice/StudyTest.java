package com.hg.junit5practice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudyTest {

    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
    }
}