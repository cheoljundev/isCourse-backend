package com.iscourse.api.service;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberCourse;
import com.iscourse.api.domain.member.MemberCourseLike;
import com.iscourse.api.exception.DuplicateCourseException;
import com.iscourse.api.repository.course.CourseRepository;
import com.iscourse.api.repository.member.MemberCourseLikeRepository;
import com.iscourse.api.repository.member.MemberCourseRepository;
import com.iscourse.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final MemberCourseLikeRepository memberCourseLikeRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final MemberCourseRepository memberCourseRepository;

    @Transactional
    public void like(Long courseId, Long memberId) {
        // 좋아요 기능 구현
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        Optional<MemberCourseLike> memberCourseLike = memberCourseLikeRepository.findByMemberAndCourse(member, course);
        System.out.println("memberCourseLike = " + memberCourseLike);
        if (!memberCourseLike.isEmpty()) {
            memberCourseLikeRepository.delete(memberCourseLike.get());
            course.subtractLike();
        } else {
            memberCourseLikeRepository.save(new MemberCourseLike(member, course));
            course.addLike();
        }
    }

    @Transactional
    public void selectCourse(Long courseId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        memberCourseRepository.findByMemberAndCourse(member, course).ifPresent(memberCourse -> {
            throw new DuplicateCourseException("이미 등록된 코스입니다.");
        });

        memberCourseRepository.save(new MemberCourse(member, course));
    }
}