package com.hg.junit5practice.junit5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 단위 테스트의 경우 테스트 각각의 의존성이나 순서에 의존하지 않아야되지만, 통합테스트, 유스케이스를 테스트 할 때는 순서를 보장해야 될 필요가 있다.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class) // 테스트의 순서를 정하는 전략 설정
public class Study8Test {

    @Order(1)
    @Test
    @DisplayName("order 1 테스트")
    void order_test() {
        System.out.println("order 1");
    }

    @Order(2)
    @Test
    @DisplayName("order 2 테스트")
    void order_test_2() {
        System.out.println("order 2");
    }

    @Order(3)
    @Test
    @DisplayName("order 3 테스트")
    void order_test_3() {
        System.out.println("order 3");
    }

    @Order(4)
    @Test
    @DisplayName("order 4 테스트")
    void order_test_4() {
        System.out.println("order 4");
    }
}
