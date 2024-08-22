package com.iscourse.api.repository.member;

import com.iscourse.api.domain.course.QCourseTag;
import com.iscourse.api.domain.course.dto.CourseFrontListDto;
import com.iscourse.api.domain.course.dto.QCourseFrontListDto;
import com.iscourse.api.domain.member.MemberCourse;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.course.QCoursePlace.coursePlace;
import static com.iscourse.api.domain.course.QCourseTag.courseTag;
import static com.iscourse.api.domain.member.QMemberCourse.memberCourse;

@Repository
@RequiredArgsConstructor
public class MemberCourseQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<CourseFrontListDto> getMemberSelectedList(Long memberId, Pageable pageable) {

        List<CourseFrontListDto> contents = queryFactory
                .select(new QCourseFrontListDto(
                        memberCourse.course,
                        JPAExpressions
                                .select(coursePlace.place.state.name)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(memberCourse.course.id))
                                .limit(1),
                        JPAExpressions
                                .select(coursePlace.place.image)
                                .from(coursePlace)
                                .where(coursePlace.course.id.eq(memberCourse.course.id))
                                .limit(1)
                ))
                .from(memberCourse)
                .where(memberCourse.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        contents.forEach(courseFrontListDto -> {
            courseFrontListDto.setTags(queryFactory
                    .select(courseTag.tag.name)
                    .from(courseTag)
                    .where(courseTag.course.id.eq(courseFrontListDto.getId()))
                    .fetch());
        });

        List<MemberCourse> countQuery = queryFactory
                .selectFrom(memberCourse)
                .where(memberCourse.member.id.eq(memberId))
                .fetch();

        int total = countQuery.size();

        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
