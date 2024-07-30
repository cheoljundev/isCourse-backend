package com.iscourse.api.repository.member;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberCourseLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberCourseLikeRepository extends JpaRepository<MemberCourseLike, Long>{
    Optional<MemberCourseLike> findByMemberAndCourse(Member member, Course course);
}
