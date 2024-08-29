package com.iscourse.api.repository.course;

import com.iscourse.api.controller.dto.course.PlaceSearchConditionDto;
import com.iscourse.api.domain.QTag;
import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.*;
import com.iscourse.api.domain.course.dto.*;
import com.iscourse.api.domain.dto.QTagDto;
import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.repository.TagRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.iscourse.api.domain.QTag.tag;
import static com.iscourse.api.domain.course.QCity.*;
import static com.iscourse.api.domain.course.QCity.city;
import static com.iscourse.api.domain.course.QLargeCategory.largeCategory;
import static com.iscourse.api.domain.course.QMiddleCategory.middleCategory;
import static com.iscourse.api.domain.course.QPlace.place;
import static com.iscourse.api.domain.course.QPlaceType.placeType;
import static com.iscourse.api.domain.course.QState.state;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PlaceQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final PlaceTypeRepository placeTypeRepository;
    private final LargeCategoryRepository largeCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final TagRepository tagRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final PlaceRepository placeRepository;

    public Page<PlaceListDto> adminList(PlaceSearchConditionDto condition, Pageable pageable) {
        List<PlaceListDto> contents = queryFactory
                .select(new QPlaceListDto(
                        place.id,
                        place.name,
                        place.image
                ))
                .from(place)
                .where(
                        place.enabled.isTrue(),
                        placeTypeEq(condition.getPlaceTypeCode()),
                        largeCategoryEq(condition.getLargeCategoryCode()),
                        middleCategoryEq(condition.getMiddleCategoryCode()),
                        tagEq(condition.getTagCode()),
                        stateEq(condition.getStateCode()),
                        cityEq(condition.getCityCode()),
                        nameLike(condition.getName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Place> countQuery = queryFactory
                .selectFrom(place)
                .where(
                        place.enabled.isTrue(),
                        placeTypeEq(condition.getPlaceTypeCode()),
                        largeCategoryEq(condition.getLargeCategoryCode()),
                        middleCategoryEq(condition.getMiddleCategoryCode()),
                        tagEq(condition.getTagCode()),
                        stateEq(condition.getStateCode()),
                        cityEq(condition.getCityCode()),
                        nameLike(condition.getName())
                )
                .fetch();


        int total = countQuery.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }

    public List<PlaceTypeDto> getPlaceTypes() {
        return queryFactory
                .select(new QPlaceTypeDto(
                        placeType.code,
                        placeType.name
                ))
                .from(placeType)
                .fetch();
    }

    public List<LargeCategoryDto> getLargeCategory(String parentCode) {
        return queryFactory
                .select(new QLargeCategoryDto(
                        largeCategory.code,
                        largeCategory.name
                ))
                .from(largeCategory)
                .where(largeCategory.parent.code.eq(parentCode))
                .fetch();
    }

    public List<MiddleCategoryDto> getMiddleCategory(String parentCode) {
        return queryFactory
                .select(new QMiddleCategoryDto(
                        middleCategory.code,
                        middleCategory.name
                ))
                .from(middleCategory)
                .where(middleCategory.parent.code.eq(parentCode))
                .fetch();
    }

    public List<TagDto> getTags(String parentCode) {
        return queryFactory
                .select(new QTagDto(
                        tag.code,
                        tag.name
                ))
                .from(tag)
                .where(tag.parent.code.eq(parentCode))
                .fetch();
    }

    public List<StateDto> getState() {
        return queryFactory
                .select(new QStateDto(
                        state.code,
                        state.name
                ))
                .from(state)
                .fetch();
    }

    public List<CityDto> getCity(String parentCode) {
        return queryFactory
                .select(new QCityDto(
                        city.code,
                        city.name
                ))
                .from(city)
                .where(city.parent.code.eq(parentCode))
                .fetch();
    }

    public void addPlace(List<AddPlaceDto> addPlaceDtoList) {
        for (AddPlaceDto addPlaceDto : addPlaceDtoList) {
            PlaceType placeType = placeTypeRepository.findByCode(addPlaceDto.getPlaceTypeCode());
            LargeCategory largeCategory = queryFactory
                    .selectFrom(QLargeCategory.largeCategory)
                    .where(
                            QLargeCategory.largeCategory.code.eq(addPlaceDto.getLargeCategoryCode()),
                            QLargeCategory.largeCategory.parent.code.eq(addPlaceDto.getPlaceTypeCode())
                    )
                    .fetchFirst();
            MiddleCategory middleCategory = queryFactory
                    .selectFrom(QMiddleCategory.middleCategory)
                    .where(
                            QMiddleCategory.middleCategory.code.eq(addPlaceDto.getMiddleCategoryCode()),
                            QMiddleCategory.middleCategory.parent.code.eq(addPlaceDto.getLargeCategoryCode())
                    )
                    .fetchFirst();
            Tag tag = queryFactory
                    .selectFrom(QTag.tag)
                    .where(
                            QTag.tag.code.eq(addPlaceDto.getTagCode()),
                            QTag.tag.parent.code.eq(addPlaceDto.getMiddleCategoryCode())
                    )
                    .fetchFirst();
            State state = stateRepository.findByCode(addPlaceDto.getStateCode());
            City city = queryFactory
                    .selectFrom(QCity.city)
                    .where(
                            QCity.city.code.eq(addPlaceDto.getCityCode()),
                            QCity.city.parent.code.eq(addPlaceDto.getStateCode())
                    )
                    .fetchFirst();
            Optional<Place> findPlace = placeRepository.findByName(addPlaceDto.getTitle());
            if (findPlace.isEmpty()) {
                Place place = new Place(
                        placeType,
                        addPlaceDto.getTitle(),
                        largeCategory,
                        middleCategory,
                        tag,
                        state,
                        city,
                        addPlaceDto.getAddress1(),
                        addPlaceDto.getAddress2(),
                        addPlaceDto.getZipcode(),
                        addPlaceDto.getMapx(),
                        addPlaceDto.getMapy(),
                        addPlaceDto.getTel(),
                        addPlaceDto.getImage()
                );
                placeRepository.save(place);
            }
        }

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
