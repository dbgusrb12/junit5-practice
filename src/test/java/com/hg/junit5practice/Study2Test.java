package com.hg.junit5practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // 테스트 이름 설정 (메서드 이름의 언더바를 공백으로 치환)
public class Study2Test {

    @Test
    void create_new_study() {
        System.out.println("create new study");
    }

    @Test
    void create_new_study_again() {
        System.out.println("create new study again");
    }

    @Test
    @DisplayName("스터디 생성 \uD83D\uDE31 🧐")
        // 테스트 별 이름 설정 (이모지도 가능)
    void create_new_study_1() {
        System.out.println("create new study 1");
    }
}
