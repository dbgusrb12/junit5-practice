package com.hg.junit5practice.junit5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FindSlowTestExtension.class) // 확장 모델 선언적인 등록
public class Study9Test {

    // 확장 모델 프로그래밍 등록
//    @RegisterExtension
//    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    @DisplayName("custom extension 테스트")
    void extension_test() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("extension test");
    }
}
