package com.iscourse.api.repository.course;

import com.iscourse.api.controller.dto.course.PlaceSearchConditionDto;
import com.iscourse.api.domain.course.dto.PlaceListDto;
import com.iscourse.api.domain.course.dto.QPlaceListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.course.QPlace.place;

@Repository
@RequiredArgsConstructor
public class PlaceQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<PlaceListDto> adminList(PlaceSearchConditionDto condition, Pageable pageable) {
        JPAQuery<PlaceListDto> query = queryFactory
                .select(new QPlaceListDto(
                        place.id,
                        place.name,
                        place.image
                ))
                .from(place)
                .where(
                        placeTypeEq(condition.getPlaceTypeCode()),
                        largeCategoryEq(condition.getLargeCategoryCode()),
                        middleCategoryEq(condition.getMiddleCategoryCode()),
                        tagEq(condition.getTagCode()),
                        stateEq(condition.getStateCode()),
                        cityEq(condition.getCityCode()),
                        nameLike(condition.getName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<PlaceListDto> contents = query.fetch();

        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }

    private BooleanExpression placeTypeEq(String placeTypeCode) {
        return placeTypeCode != null ? place.placeType.code.eq(placeTypeCode) : null;
    }

    private BooleanExpression largeCategoryEq(String largeCategoryCode) {
        return largeCategoryCode != null ? place.largeCategory.code.eq(largeCategoryCode) : null;
    }

    private BooleanExpression middleCategoryEq(String middleCategoryCode) {
        return middleCategoryCode != null ? place.middleCategory.code.eq(middleCategoryCode) : null;
    }

    private BooleanExpression tagEq(String tagCode) {
        return tagCode != null ? place.tag.code.eq(tagCode) : null;
    }

    private BooleanExpression stateEq(String stateCode) {
        return stateCode != null ? place.state.code.eq(stateCode) : null;
    }

    private BooleanExpression cityEq(String cityCode) {
        return cityCode != null ? place.city.code.eq(cityCode) : null;
    }

    private BooleanExpression nameLike(String name) {
        return name != null ? place.name.contains(name) : null;
    }
}