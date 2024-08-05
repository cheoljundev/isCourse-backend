package com.iscourse.api.service;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.CoursePlace;
import com.iscourse.api.domain.course.Place;
import com.iscourse.api.controller.CourseShareDto;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberCourse;
import com.iscourse.api.domain.member.MemberCourseLike;
import com.iscourse.api.domain.member.MemberRoleType;
import com.iscourse.api.exception.DuplicateCourseException;
import com.iscourse.api.exception.UnavailableEntityException;
import com.iscourse.api.repository.course.CoursePlaceRepository;
import com.iscourse.api.repository.course.CourseRepository;
import com.iscourse.api.repository.course.PlaceRepository;
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
    private final PlaceRepository placeRepository;
    private final CoursePlaceRepository coursePlaceRepository;

    @Transactional
    public void like(Long courseId, Long memberId) {
        // 좋아요 기능 구현
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);

        if (!course.getEnabled()) {
            throw new UnavailableEntityException("비활성화된 코스입니다.");
        }

        Optional<MemberCourseLike> memberCourseLike = memberCourseLikeRepository.findByMemberAndCourse(member, course);
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

        if (!course.getEnabled()) {
            throw new UnavailableEntityException("비활성화된 코스입니다.");
        }

        memberCourseRepository.findByMemberAndCourse(member, course).ifPresent(memberCourse -> {
            throw new DuplicateCourseException("이미 등록된 코스입니다.");
        });

        memberCourseRepository.save(new MemberCourse(member, course));
    }

    @Transactional
    public void share(CourseShareDto courseShareDto) {
        Course course = new Course(
                courseShareDto.getName(),
                courseShareDto.getHour(),
                courseShareDto.getMinute(),
                courseShareDto.getIntroduce(),
                MemberRoleType.ROLE_USER
        );
        courseRepository.save(course);
        for (int i = 0; i < courseShareDto.getPlaceIdList().size(); i++) {
            Place place = placeRepository.findById(courseShareDto.getPlaceIdList().get(i)).orElseThrow(IllegalArgumentException::new);

            if (!place.getEnabled()) {
                throw new UnavailableEntityException("비활성화된 장소입니다.");
            }

            CoursePlace coursePlace = new CoursePlace(
                    course,
                    place,
                    i
            );
            coursePlaceRepository.save(coursePlace);
        }
    }
}
