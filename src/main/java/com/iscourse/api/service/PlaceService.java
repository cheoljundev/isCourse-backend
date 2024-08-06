package com.iscourse.api.service;

import com.iscourse.api.controller.dto.course.TourApiSearchConditionDto;
import com.iscourse.api.domain.course.Place;
import com.iscourse.api.domain.course.dto.ApiResponse;
import com.iscourse.api.domain.course.dto.PlaceSelectListDto;
import com.iscourse.api.repository.course.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public void deletePlace(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        place.delete();
    }

    public Page<PlaceSelectListDto> getPlaceByApi(TourApiSearchConditionDto condition, Pageable pageable) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        WebClient webClient = webClientBuilder
                // URI 인코딩을 수동으로 처리하기 위해 UriBuilderFactory 설정
                .uriBuilderFactory(factory)
                .build();

        String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";


        // 서비스 키: URL 인코딩되지 않은 값
        String serviceKey = "0PgiN9t3WrHpyoD/zJvKmTRjB3mbJ7WEJ/we0+YI/gi4mKYmYAXexISY9Zq9BiEr8nbk+2v6dHZw8KZtYeBaRA==";
        String encodedServiceKey = UriUtils.encode(serviceKey, StandardCharsets.UTF_8);

        // 쿼리 파라미터를 수동으로 추가하여 URL 생성
        StringBuilder queryString = new StringBuilder("?MobileOS=ETC");
        queryString.append("&MobileApp=isCourse");
        queryString.append("&listYN=Y");
        queryString.append("&arrange=A");
        queryString.append("&numOfRows=").append(pageable.getPageSize());
        queryString.append("&ServiceKey=").append(encodedServiceKey);
        queryString.append("&contentTypeId=").append(condition.getPlaceTypeCode() == null ? "" : condition.getPlaceTypeCode());
        queryString.append("&areaCode=").append(condition.getStateCode() == null ? "" : condition.getStateCode());
        queryString.append("&sigunguCode=").append(condition.getCityCode() == null ? "" : condition.getCityCode());
        queryString.append("&cat1=").append(condition.getLargeCategoryCode() == null ? "" : condition.getLargeCategoryCode());
        queryString.append("&cat2=").append(condition.getMiddleCategoryCode() == null ? "" : condition.getMiddleCategoryCode());
        queryString.append("&cat3=").append(condition.getTagCode() == null ? "" : condition.getTagCode());
        queryString.append("&pageNo=").append(pageable.getPageNumber());
        queryString.append("&_type=json");

        String uri = url + queryString.toString();

        log.info("uri: {}", uri);

        ApiResponse.Body body = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .map(response -> response.getResponse().getBody()).block();

        int totalCount = body.getTotalCount();

        List<ApiResponse.Item> getPlaceList = body.getItems().getItemList();

        List<Place> placeRepositoryAll = placeRepository.findAll();

        List<PlaceSelectListDto> filterList = getPlaceList.stream()
                .filter(
                        item -> placeRepositoryAll.stream()
                                .noneMatch(place -> place.getName().equals(item.getName()))
                )
                .map(item -> {
                    PlaceSelectListDto placeSelectListDto = new PlaceSelectListDto(item);
                    return placeSelectListDto;
                })
                .collect(Collectors.toList());

        return PageableExecutionUtils.getPage(filterList, pageable, () -> totalCount);
    }
}
