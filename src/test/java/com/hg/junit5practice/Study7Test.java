package com.hg.junit5practice;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS) // test 인스턴스를 클래스 단위로 만듬
public class Study7Test {

    int value = 0;

    @BeforeAll // test 인스턴스가 class 단위로 생성이 된다면 static 키워드가 필요 없다.
    void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll // test 인스턴스가 class 단위로 생성이 된다면 static 키워드가 필요 없다.
    void afterAll() {
        System.out.println("after all");
    }

    @Test
    @DisplayName("인스턴스 테스트")
    void instance_test() {
        System.out.println("test1 " + value++);
    }

    @Test
    @DisplayName("인스턴스 테스트")
    void instance_test_2() {
        System.out.println("test2 " + value++);
    }
}
