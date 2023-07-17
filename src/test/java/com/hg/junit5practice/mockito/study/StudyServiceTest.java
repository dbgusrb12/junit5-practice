package com.hg.junit5practice.mockito.study;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import com.hg.junit5practice.mockito.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("Mock 객체 생성 (mock 메서드)")
    void create_study_service_with_method() {
        // Mockito.mock(Class<T> classToMock) 메서드를 사용하여 mock 객체 생성
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    @DisplayName("Mock 객체 생성 (@Mock 어노테이션)")
    void create_study_service_with_annotation() {
        // MockitoExtension 확장 모델 적용 후 @Mock 어노테이션으로 mock 객체 생성
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    @DisplayName("Mock 객체 생성 (@Mock 어노테이션, 파라미터 기반)")
    void create_study_service_with_annotation_and_parameter(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        // @Mock 어노테이션으로 mock 객체 생성 (파라미터로 받을 수도 있다.
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }


}