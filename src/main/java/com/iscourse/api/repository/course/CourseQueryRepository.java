package com.iscourse.api.repository.course;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.dto.*;
import com.iscourse.api.domain.member.MemberRoleType;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
        JPAQuery<CourseFrontListDto> query = queryFactory
                .select(new QCourseFrontListDto(
                        course,
                        JPAExpressions
                                .select(coursePlace.place.state.name)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(course.id))
                                .limit(1),
                        JPAExpressions
                                .select(coursePlace.place.image)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(course.id))
                                .limit(1)
                ))
                .from(course)
                .where(course.courseType.eq(MemberRoleType.ROLE_USER), course.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<CourseFrontListDto> contents = query.fetch();

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);

    }

    public Page<CourseFrontListDto> getMemberSharedList(Long memberId, Pageable pageable) {

        JPAQuery<CourseFrontListDto> query = queryFactory
                .select(new QCourseFrontListDto(
                        course,
                        JPAExpressions
                                .select(coursePlace.place.state.name)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(course.id))
                                .limit(1),
                        JPAExpressions
                                .select(coursePlace.place.image)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(course.id))
                                .limit(1)
                ))
                .from(course)
                .where(course.createdBy.id.eq(memberId), course.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<CourseFrontListDto> contents = query.fetch();

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }

    public Page<CourseFrontListDto> recommendCourse(double mapx, double mapy, Integer maxDistance) {
        NumberExpression<Double> distance = Expressions.numberTemplate(
                Double.class,
                "6371 * ACOS(COS(RADIANS({0})) * COS(RADIANS({1})) * COS(RADIANS({2}) - RADIANS({3})) + SIN(RADIANS({0})) * SIN(RADIANS({1}))) * 1000",
                mapx, course.mapx, course.mapy, mapy
        );

        JPAQuery<CourseFrontListDto> query = queryFactory
                .select(new QCourseFrontListDto(
                        course,
                        JPAExpressions
                                .select(coursePlace.place.state.name)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(course.id))
                                .limit(1),
                        JPAExpressions
                                .select(coursePlace.place.image)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(course.id))
                                .limit(1)
                ))
                .from(course)
                .where(course.courseType.eq(MemberRoleType.ROLE_MANAGER), course.enabled.eq(true), distance.loe(maxDistance))
                .offset(0)
                .limit(10);

        List<CourseFrontListDto> contents = query.fetch();

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, Pageable.unpaged(), () -> total);
    }
}
