package com.hg.junit5practice.junit5;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class Study4Test {

    private static final String ENV = "HYUNGYU";

    @Test
    @DisplayName("스터디 생성 테스트 내부 에서 분기")
    void create_new_study_in_method() {
        Study study = new Study();

        // ENV 값이 LOCAL 이면 테스트 진행
        assumeTrue("LOCAL".equalsIgnoreCase(ENV));

        assertNotNull(study);
    }

    @Test
    @EnabledOnOs(OS.MAC) // OS 가 MAC 이면 테스트 진행
    @DisplayName("스터디 생성 MAC OS 만 실행")
    void create_new_study_OS() {
        Study study = new Study();
        assertNotNull(study);
    }

    @Test
    @DisabledOnOs(OS.MAC) // OS 가 MAC 이면 테스트 안함
    @DisplayName("스터디 생성 MAC OS 만 실행 안함")
    void create_new_study_OS_2() {
        Study study = new Study();
        assertNotNull(study);
    }

    // 그 외 다양한 어노테이션을 제공한다.
}
