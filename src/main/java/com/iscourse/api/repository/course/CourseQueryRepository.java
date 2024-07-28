package com.iscourse.api.repository.course;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.Place;
import com.iscourse.api.domain.course.dto.*;
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
                .where(course.id.eq(id))
                .fetchOne();

        List<Tag> tags = queryFactory
                .select(courseTag.tag)
                .from(courseTag)
                .where(courseTag.course.id.eq(id))
                .fetch();

        List<CoursePlaceDto> coursePlaceList = queryFactory
                .select(new QCoursePlaceDto(
                        coursePlace.place.state.name,
                        coursePlace.place.name,
                        coursePlace.place.image,
                        coursePlace.position
                ))
                .from(coursePlace)
                .where(coursePlace.course.id.eq(id))
                .fetch();

        tags.forEach(tag -> courseFrontDto.getTags().add(tag.getName()));
        coursePlaceList.forEach(coursePlace -> courseFrontDto.getCoursePlaces().add(coursePlace));
        courseFrontDto.setImage(coursePlaceList.get(0).getImage());
        courseFrontDto.setState(coursePlaceList.get(0).getState());

        return courseFrontDto;
    }

    public Page<CourseFrontListDto> frontList(Pageable pageable) {
        JPAQuery<CourseFrontListDto> query = queryFactory
                .select(new QCourseFrontListDto(course))
                .from(course)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<CourseFrontListDto> contents = query.fetch();

        contents.forEach(content -> {
            Place place = queryFactory
                    .select(coursePlace.place)
                    .from(coursePlace)
                    .where(coursePlace.course.id.eq(content.getId()))
                    .fetchOne();

            content.setState(place.getState().getName());
            content.setImage(place.getImage());
        });

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);

        // todo: 조건에 따른 검색 추가 필요
    }
}
