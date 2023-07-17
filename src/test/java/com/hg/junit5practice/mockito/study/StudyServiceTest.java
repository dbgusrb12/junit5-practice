package com.hg.junit5practice.mockito.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.hg.junit5practice.mockito.domain.Member;
import com.hg.junit5practice.mockito.domain.Study;
import com.hg.junit5practice.mockito.member.MemberService;
import java.util.Optional;
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

    @Test
    @DisplayName("Mockito return 테스트")
    void mockito_return_test() {
        // return type 이 optional 일 경우 Optional.empty 반환
        Optional<Member> optional = memberService.findById(1L);
        // return type 이 void 면 아무 일도 일어나지 않음.
        memberService.validate(1L);
        assertTrue(optional.isEmpty());
    }

    @Test
    @DisplayName("Mockito Stubbing 테스트")
    void mockito_stubbing_test() {
//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hg@email.com");
        // memberService.findById(1L) 이 호출되면 Optional.of(member) 를 리턴하게끔 설정
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        Optional<Member> byId = memberService.findById(1L);
        assertTrue(byId.isPresent());
        assertEquals("hg@email.com", byId.get().getEmail());
    }

    @Test
    @DisplayName("Mockito Stubbing 테스트 2")
    void mockito_stubbing_test_2() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("hg@email.com");
        // memberService.findById() 아무 파라미터나 넣고 호출되면 Optional.of(member) 를 리턴하게끔 설정
        when(memberService.findById(any())).thenReturn(Optional.of(member));

        Optional<Member> byId = memberService.findById(1L);
        assertTrue(byId.isPresent());
        assertEquals("hg@email.com", byId.get().getEmail());

        Optional<Member> byId2 = memberService.findById(2L);
        assertTrue(byId2.isPresent());
        assertEquals("hg@email.com", byId2.get().getEmail());
    }

    @Test
    @DisplayName("Mockito Stubbing 테스트 3")
    void mockito_stubbing_test_3() {
        // memberService.validate(1L) 호출 시 IllegalArgumentException 에러 발생
        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

        assertThrows(IllegalArgumentException.class, () -> memberService.validate(1L));

        memberService.validate(2L);
    }

    @Test
    @DisplayName("Mockito Stubbing 테스트 4")
    void mockito_stubbing_test_4() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("hg@email.com");
        // memberService.findById() 가 호출 할 때 마다 변경되는 값 설정
        when(memberService.findById(any()))
            .thenReturn(Optional.of(member))
            .thenThrow(new RuntimeException())
            .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertTrue(byId.isPresent());
        assertEquals("hg@email.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> memberService.findById(1L));

        Optional<Member> byId2 = memberService.findById(1L);
        assertTrue(byId2.isEmpty());
    }

    @Test
    @DisplayName("스터디 생성 테스트")
    void create_study_test() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hg@email.com");

        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);
        assertEquals(member, study.getOwner());
    }

}