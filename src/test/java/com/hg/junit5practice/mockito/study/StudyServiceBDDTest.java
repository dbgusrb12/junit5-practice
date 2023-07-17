package com.hg.junit5practice.mockito.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
class StudyServiceBDDTest {

    @Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("BDD 스타일의 스터디 생성 테스트")
    void create_study_test_BDD() {
        // given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hg@email.com");

        Study study = new Study(10, "테스트");

        // BDD 스타일의 stubbing (given 구문인데 when 메서드를 써서 형식에 맞지 않는 부분을 수정)
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // when
        studyService.createNewStudy(1L, study);

        // then
        assertEquals(member, study.getOwner());
        then(memberService).should(times(1)).notify(study);
    }

}