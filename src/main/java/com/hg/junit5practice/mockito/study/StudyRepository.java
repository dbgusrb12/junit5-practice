package com.hg.junit5practice.mockito.study;

import com.hg.junit5practice.mockito.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
