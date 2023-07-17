package com.hg.junit5practice.mockito.member;

import com.hg.junit5practice.mockito.domain.Member;
import com.hg.junit5practice.mockito.domain.Study;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
