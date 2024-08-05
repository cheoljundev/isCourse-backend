package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.deal.QSalesDetail;
import com.iscourse.api.domain.deal.dto.QSalesDetailDto;
import com.iscourse.api.domain.deal.dto.SalesDetailDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.deal.QSalesDetail.*;

@Repository
@RequiredArgsConstructor
public class SalesDetailQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<SalesDetailDto> findList(Pageable pageable) {
        JPAQuery<SalesDetailDto> query = queryFactory
                .select(new QSalesDetailDto(salesDetail))
                .from(salesDetail)
                .where(salesDetail.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<SalesDetailDto> contents = query.fetch();

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
