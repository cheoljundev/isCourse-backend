package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class StateRepositoryTest {
    @Autowired StateRepository stateRepository;

    @Test
    public void save() {
        // given
        State state = new State("1", "서울시");
        //when
        stateRepository.save(state);
        //then
    }
}