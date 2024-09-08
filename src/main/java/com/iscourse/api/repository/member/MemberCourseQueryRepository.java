package com.iscourse.api.repository.member;

import com.iscourse.api.domain.course.QCourseTag;
import com.iscourse.api.domain.course.dto.CourseFrontListDto;
import com.iscourse.api.domain.course.dto.QCourseFrontListDto;
import com.iscourse.api.domain.member.MemberCourse;
import com.querydsl.core.types.dsl.Expressions;
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
        try {

            // state와 image를 Expression으로 감싸서 전달
            List<CourseFrontListDto> contents = queryFactory
                    .select(new QCourseFrontListDto(
                            memberCourse.course
                    ))
                    .from(memberCourse)
                    .where(memberCourse.member.id.eq(memberId))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            // 태그 설정
            contents.forEach(courseFrontListDto -> {
                courseFrontListDto.setTags(queryFactory
                        .select(courseTag.tag.name)
                        .from(courseTag)
                        .where(courseTag.course.id.eq(courseFrontListDto.getId()))
                        .fetch());
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

            // 총 개수 계산
            List<MemberCourse> countQuery = queryFactory
                    .selectFrom(memberCourse)
                    .where(memberCourse.member.id.eq(memberId))
                    .fetch();

            int total = countQuery.size();

            return PageableExecutionUtils.getPage(contents, pageable, () -> total);

        } catch (Exception e) {
            // 예외 발생 시 로깅
            e.printStackTrace();  // 개발 단계에서 예외를 콘솔에 출력
            // 오류 페이지 또는 빈 결과 반환
            return Page.empty(pageable);  // 빈 페이지를 반환하거나 에러 응답을 만들어서 처리할 수 있습니다.
        }
    }

}
