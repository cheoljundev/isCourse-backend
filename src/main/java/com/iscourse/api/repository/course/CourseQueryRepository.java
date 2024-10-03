package com.iscourse.api.repository.course;

import com.iscourse.api.controller.dto.course.CourseSearchConditionDto;
import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.dto.*;
import com.iscourse.api.domain.member.MemberRoleType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.course.QCourse.course;
import static com.iscourse.api.domain.course.QCoursePlace.coursePlace;
import static com.iscourse.api.domain.course.QCourseTag.courseTag;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CourseQueryRepository {
    private final JPAQueryFactory queryFactory;

    public CourseFrontDto frontDetail(Long id) {
        CourseFrontDto courseFrontDto = queryFactory
                .select(new QCourseFrontDto(course))
                .from(course)
                .where(course.id.eq(id), course.enabled.eq(true))
                .fetchOne();

        List<Tag> tags = queryFactory
                .select(courseTag.tag)
                .from(courseTag)
                .where(courseTag.course.id.eq(id), courseTag.enabled.eq(true))
                .fetch();

        List<CoursePlaceDto> coursePlaceList = queryFactory
                .select(new QCoursePlaceDto(
                        coursePlace.place.state.name,
                        coursePlace.place.name,
                        coursePlace.place.image,
                        coursePlace.position
                ))
                .from(coursePlace)
                .where(coursePlace.course.id.eq(id), coursePlace.enabled.eq(true))
                .fetch();

        tags.forEach(tag -> courseFrontDto.getTags().add(tag.getName()));
        coursePlaceList.forEach(coursePlace -> courseFrontDto.getCoursePlaces().add(coursePlace));
        courseFrontDto.setImage(coursePlaceList.get(0).getImage());
        courseFrontDto.setState(coursePlaceList.get(0).getState());

        return courseFrontDto;
    }

    public Page<CourseFrontListDto> frontList(Pageable pageable) {
        List<CourseFrontListDto> contents = queryFactory
                .select(new QCourseFrontListDto(
                        course
                ))
                .from(course)
                .where(course.courseType.eq(MemberRoleType.ROLE_USER), course.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        contents.forEach(courseFrontListDto -> {
            List<Tag> tags = queryFactory
                    .select(courseTag.tag)
                    .from(courseTag)
                    .where(courseTag.course.id.eq(courseFrontListDto.getId()), courseTag.enabled.eq(true))
                    .fetch();
            tags.forEach(tag -> courseFrontListDto.getTags().add(tag.getName()));
            courseFrontListDto.setState(queryFactory
                    .select(coursePlace.place.state.name)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseFrontListDto.getId()))
                    .fetchFirst());
            courseFrontListDto.setImage(queryFactory
                    .select(coursePlace.place.image)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseFrontListDto.getId()))
                    .fetchFirst());
        });

        List<Course> countQuery = queryFactory
                .selectFrom(course)
                .where(course.courseType.eq(MemberRoleType.ROLE_USER), course.enabled.eq(true))
                .fetch();

        int total = countQuery.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);

    }

    public Page<CourseFrontListDto> getMemberSharedList(Long memberId, Pageable pageable) {

        List<CourseFrontListDto> contents = queryFactory
                .select(new QCourseFrontListDto(
                        course
                ))
                .from(course)
                .where(course.createdBy.id.eq(memberId), course.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        contents.forEach(courseFrontListDto -> {
            List<Tag> tags = queryFactory
                    .select(courseTag.tag)
                    .from(courseTag)
                    .where(courseTag.course.id.eq(courseFrontListDto.getId()), courseTag.enabled.eq(true))
                    .fetch();
            tags.forEach(tag -> courseFrontListDto.getTags().add(tag.getName()));
            courseFrontListDto.setState(queryFactory
                    .select(coursePlace.place.state.name)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseFrontListDto.getId()))
                    .fetchFirst());
            courseFrontListDto.setImage(queryFactory
                    .select(coursePlace.place.image)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseFrontListDto.getId()))
                    .fetchFirst());
        });

        List<Course> countQuery = queryFactory
                .selectFrom(course)
                .where(course.createdBy.id.eq(memberId), course.enabled.eq(true))
                .fetch();

        long total = countQuery.size();

        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }

    public Page<CourseFrontListDto> recommendCourse(double mapx, double mapy, Integer maxDistance, Pageable pageable) {
        NumberExpression<Double> distance = Expressions.numberTemplate(
                Double.class,
                "6371 * ACOS(COS(RADIANS({0})) * COS(RADIANS({1})) * COS(RADIANS({2}) - RADIANS({3})) + SIN(RADIANS({0})) * SIN(RADIANS({1}))) * 1000",
                mapx, course.mapx, course.mapy, mapy
        );

        List<CourseFrontListDto> contents = queryFactory
                .select(new QCourseFrontListDto(
                        course
                ))
                .from(course)
                .where(course.courseType.eq(MemberRoleType.ROLE_MANAGER), course.enabled.eq(true), distance.loe(maxDistance))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        contents.forEach(courseFrontListDto -> {
            List<Tag> tags = queryFactory
                    .select(courseTag.tag)
                    .from(courseTag)
                    .where(courseTag.course.id.eq(courseFrontListDto.getId()), courseTag.enabled.eq(true))
                    .fetch();
            tags.forEach(tag -> courseFrontListDto.getTags().add(tag.getName()));
            courseFrontListDto.setState(queryFactory
                    .select(coursePlace.place.state.name)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseFrontListDto.getId()))
                    .fetchFirst());
            courseFrontListDto.setImage(queryFactory
                    .select(coursePlace.place.image)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseFrontListDto.getId()))
                    .fetchFirst());
        });

        List<Course> countQuery = queryFactory
                .selectFrom(course)
                .where(course.courseType.eq(MemberRoleType.ROLE_MANAGER), course.enabled.eq(true), distance.loe(maxDistance))
                .fetch();

        int total = countQuery.size();
        return PageableExecutionUtils.getPage(contents, Pageable.unpaged(), () -> total);
    }

    public CourseAdminDto adminDetail(Long id) {
        CourseAdminDto courseAdminDto = queryFactory
                .select(new QCourseAdminDto(course))
                .from(course)
                .where(course.id.eq(id), course.enabled.eq(true))
                .fetchOne();

        List<Tag> tags = queryFactory
                .select(courseTag.tag)
                .from(courseTag)
                .where(courseTag.course.id.eq(id), courseTag.enabled.eq(true))
                .fetch();

        List<CoursePlaceDto> coursePlaceList = queryFactory
                .select(new QCoursePlaceDto(
                        coursePlace.place.state.name,
                        coursePlace.place.name,
                        coursePlace.place.image,
                        coursePlace.position
                ))
                .from(coursePlace)
                .where(coursePlace.course.id.eq(id), coursePlace.enabled.eq(true))
                .fetch();

        tags.forEach(tag -> courseAdminDto.getTags().add(tag.getName()));
        coursePlaceList.forEach(coursePlace -> courseAdminDto.getCoursePlaces().add(coursePlace));

        return courseAdminDto;
    }

    public Page<CourseAdminListDto> adminList(CourseSearchConditionDto condition, Pageable pageable) {
        JPQLQuery<Long> courseIdsByTags = condition.getTagCodeList().isEmpty() ? null : JPAExpressions
                .select(courseTag.course.id)
                .from(courseTag)
                .where(courseTag.tag.code.in(condition.getTagCodeList()));

        List<CourseAdminListDto> contents = queryFactory
                .select(new QCourseAdminListDto(
                        course
                ))
                .from(course)
                .where(
                        course.enabled.eq(true),
                        courseTagIn(courseIdsByTags),
                        courseNameLike(condition.getName()),
                        courseTypeEq(condition.getCourseType())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        contents.forEach(courseAdminListDto -> {
            courseAdminListDto.setImage(queryFactory
                    .select(coursePlace.place.image)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(courseAdminListDto.getId()))
                    .fetchFirst());
                });

        List<Course> countQuery = queryFactory
                .selectFrom(course)
                .where(
                        course.enabled.eq(true),
                        courseTagIn(courseIdsByTags),
                        courseNameLike(condition.getName()),
                        courseTypeEq(condition.getCourseType())
                )
                .fetch();

        int total = countQuery.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);

    }

    private BooleanExpression courseTagIn(JPQLQuery<Long> courseIdsByTags) {
        return courseIdsByTags == null ? null : course.id.in(courseIdsByTags);
    }

    private BooleanExpression courseTypeEq(MemberRoleType courseType) {
        return courseType == null ? null : course.courseType.eq(courseType);
    }

    private BooleanExpression courseNameLike(String name) {
        return name == null ? null : course.name.contains(name);
    }

}
