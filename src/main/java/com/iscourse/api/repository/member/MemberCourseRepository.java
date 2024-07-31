package com.iscourse.api.repository.member;

import com.iscourse.api.domain.member.MemberCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCourseRepository extends JpaRepository<MemberCourse, Long> {
}
