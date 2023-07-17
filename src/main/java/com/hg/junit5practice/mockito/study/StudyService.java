package com.hg.junit5practice.mockito.study;

import com.hg.junit5practice.mockito.domain.Member;
import com.hg.junit5practice.mockito.domain.Study;
import com.hg.junit5practice.mockito.member.MemberService;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Member member = memberService.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'"));
        study.setOwner(member);
        Study newStudy = repository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member);
        return newStudy;
    }
}
