package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.City;
import com.iscourse.api.domain.course.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class CityRepositoryTest {
    @Autowired CityRepository cityRepository;
    @Autowired StateRepository stateRepository;

    @Test
    public void save() {
        // given
        State state = stateRepository.findById(1L).get();
        City city = new City("1", "강남구", state);
        //when
        cityRepository.save(city);
        //then
    }
}