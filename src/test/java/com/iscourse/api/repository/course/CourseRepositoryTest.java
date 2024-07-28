package com.iscourse.api.repository.course;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.CoursePlace;
import com.iscourse.api.domain.course.CourseTag;
import com.iscourse.api.domain.course.Place;
import com.iscourse.api.domain.member.MemberRoleType;
import com.iscourse.api.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Commit
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    CourseTagRepository courseTagRepository;
    @Autowired
    CoursePlaceRepository coursePlaceRepository;
    @Autowired
    PlaceRepository placeRepository;

    @Test
    public void save() {
        // given
        Course course = new Course("나이스한 코스", 2, 10, "좋은 코스입니다!!", MemberRoleType.ROLE_USER);
        Tag tag = tagRepository.findById(1L).get();
        Place place = placeRepository.findById(1L).get();
        CourseTag courseTag = new CourseTag(course, tag);
        CoursePlace coursePlace = new CoursePlace(course, place, 1);
        //when
        courseRepository.save(course);
        courseTagRepository.save(courseTag);
        coursePlaceRepository.save(coursePlace);
        //then
    }
}