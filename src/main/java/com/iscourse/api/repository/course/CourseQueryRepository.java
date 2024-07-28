package com.iscourse.api.repository.course;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.dto.CourseFrontDto;
import com.iscourse.api.domain.course.dto.CoursePlaceDto;
import com.iscourse.api.domain.course.dto.QCourseFrontDto;
import com.iscourse.api.domain.course.dto.QCoursePlaceDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.course.QCourse.course;
import static com.iscourse.api.domain.course.QCoursePlace.coursePlace;
import static com.iscourse.api.domain.course.QCourseTag.courseTag;

@Repository
@RequiredArgsConstructor
public class CourseQueryRepository {
    private final EntityManager em;
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

        return courseFrontDto;
    }
}
