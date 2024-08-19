package com.iscourse.api.service;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.CoursePlace;
import com.iscourse.api.domain.course.CourseTag;
import com.iscourse.api.domain.course.Place;
import com.iscourse.api.domain.course.dto.AddCourseDto;
import com.iscourse.api.domain.member.*;
import com.iscourse.api.exception.DuplicateCourseException;
import com.iscourse.api.exception.UnavailableEntityException;
import com.iscourse.api.repository.TagRepository;
import com.iscourse.api.repository.course.CoursePlaceRepository;
import com.iscourse.api.repository.course.CourseRepository;
import com.iscourse.api.repository.course.CourseTagRepository;
import com.iscourse.api.repository.course.PlaceRepository;
import com.iscourse.api.repository.member.MemberCourseLikeRepository;
import com.iscourse.api.repository.member.MemberCourseRepository;
import com.iscourse.api.repository.member.MemberPlaceRepository;
import com.iscourse.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    private final CourseTagRepository courseTagRepository;
    private final TagRepository tagRepository;
    private final MemberPlaceRepository memberPlaceRepository;

    @Transactional
    public Integer like(Long courseId, Long memberId) {
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

        return course.getLikes();
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

        coursePlaceRepository.findByCourse(course).forEach(coursePlace -> {
            Place place = coursePlace.getPlace();

            if (!place.getEnabled()) {
                throw new UnavailableEntityException("비활성화된 장소입니다.");
            }

            Optional<MemberPlace> byMemberAndPlace = memberPlaceRepository.findByMemberAndPlace(member, place);

            if (byMemberAndPlace.isEmpty()) {
                memberPlaceRepository.save(new MemberPlace(member, place));
            }

        });

        memberCourseRepository.save(new MemberCourse(member, course));
    }

    @Transactional
    public void share(AddCourseDto addCourseDto) {
        Course course = new Course(
                addCourseDto.getName(),
                addCourseDto.getHour(),
                addCourseDto.getMinute(),
                addCourseDto.getIntroduce(),
                MemberRoleType.ROLE_USER
        );
        courseRepository.save(course);
        for (int i = 0; i < addCourseDto.getPlaceIdList().size(); i++) {
            Place place = placeRepository.findById(addCourseDto.getPlaceIdList().get(i)).orElseThrow(IllegalArgumentException::new);

            if (!place.getEnabled()) {
                throw new UnavailableEntityException("비활성화된 장소입니다.");
            }

            addCourseDto.getTagList().add(place.getTag().getCode());

            CoursePlace coursePlace = new CoursePlace(
                    course,
                    place,
                    i
            );
            coursePlaceRepository.save(coursePlace);
        }

        for (String code : addCourseDto.getTagList()) {
            Tag tag = tagRepository.findByCode(code).orElseThrow(IllegalArgumentException::new);
            CourseTag courseTag = new CourseTag(course, tag);
            courseTagRepository.save(courseTag);
        }
    }

    @Transactional
    public void addCourse(AddCourseDto addCourseDto) {
        Course course = new Course(
                addCourseDto.getName(),
                addCourseDto.getHour(),
                addCourseDto.getMinute(),
                addCourseDto.getIntroduce(),
                MemberRoleType.ROLE_MANAGER
        );
        courseRepository.save(course);
        for (int i = 0; i < addCourseDto.getPlaceIdList().size(); i++) {
            Place place = placeRepository.findById(addCourseDto.getPlaceIdList().get(i)).orElseThrow(IllegalArgumentException::new);

            if (!place.getEnabled()) {
                throw new UnavailableEntityException("비활성화된 장소입니다.");
            }

            addCourseDto.getTagList().add(place.getTag().getCode());

            CoursePlace coursePlace = new CoursePlace(
                    course,
                    place,
                    i
            );
            coursePlaceRepository.save(coursePlace);
        }

        for (String code : addCourseDto.getTagList()) {
            Tag tag = tagRepository.findByCode(code).orElseThrow(IllegalArgumentException::new);
            CourseTag courseTag = new CourseTag(course, tag);
            courseTagRepository.save(courseTag);
        }
    }

    @Transactional
    public void updateCourse(Long id, AddCourseDto addCourseDto) {
        Course course = courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        course.update(
                addCourseDto.getName(),
                addCourseDto.getHour(),
                addCourseDto.getMinute(),
                addCourseDto.getIntroduce()
        );
        courseRepository.save(course);

        coursePlaceRepository.findByCourse(course).forEach(CoursePlace::delete);
        courseTagRepository.findByCourse(course).forEach(CourseTag::delete);

        for (int i = 0; i < addCourseDto.getPlaceIdList().size(); i++) {
            Place place = placeRepository.findById(addCourseDto.getPlaceIdList().get(i)).orElseThrow(IllegalArgumentException::new);

            if (!place.getEnabled()) {
                throw new UnavailableEntityException("비활성화된 장소입니다.");
            }

            addCourseDto.getTagList().add(place.getTag().getCode());

            CoursePlace coursePlace = new CoursePlace(
                    course,
                    place,
                    i
            );
            coursePlaceRepository.save(coursePlace);
        }

        for (String code : addCourseDto.getTagList()) {
            Tag tag = tagRepository.findByCode(code).orElseThrow(IllegalArgumentException::new);
            CourseTag courseTag = new CourseTag(course, tag);
            courseTagRepository.save(courseTag);
        }
    }

    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        course.delete();
        coursePlaceRepository.findByCourse(course).forEach(CoursePlace::delete);
        courseTagRepository.findByCourse(course).forEach(CourseTag::delete);
    }
}
