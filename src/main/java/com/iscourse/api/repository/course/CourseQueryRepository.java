package com.iscourse.api.repository.course;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
}
