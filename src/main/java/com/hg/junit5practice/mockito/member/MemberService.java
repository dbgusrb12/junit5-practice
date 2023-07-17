package com.hg.junit5practice.mockito.member;

import com.hg.junit5practice.mockito.domain.Member;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void validate(Long meberId);
}
