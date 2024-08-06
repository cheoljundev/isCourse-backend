package com.iscourse.api.repository.course;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.*;
import com.iscourse.api.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class PlaceRepositoryTest {
    @Autowired PlaceRepository placeRepository;
    @Autowired PlaceTypeRepository placeTypeRepository;
    @Autowired StateRepository stateRepository;
    @Autowired CityRepository cityRepository;
    @Autowired LargeCategoryRepository largeCategoryRepository;
    @Autowired MiddleCategoryRepository middleCategoryRepository;
    @Autowired TagRepository tagRepository;


    @Test
    public void save() {
        // given
        PlaceType placeType = placeTypeRepository.findById(1L).get();
        State state = stateRepository.findById(1L).get();
        City city = cityRepository.findById(1L).get();
        LargeCategory largeCategory = largeCategoryRepository.findById(1L).get();
        MiddleCategory middleCategory = middleCategoryRepository.findById(1L).get();
        Tag tag = tagRepository.findById(1L).get();
        Place place = new Place(placeType, "제이스텔라", largeCategory, middleCategory, tag, state, city, "서울시 강남구 11", "강남빌딩 202호", "12345", 129.0598281416, 35.1448074253, "0212341234", "http://image.com/image.png");
        //when
        placeRepository.save(place);
        //then
    }
}