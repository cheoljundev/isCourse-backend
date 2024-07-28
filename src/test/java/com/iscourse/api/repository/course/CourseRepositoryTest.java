package com.iscourse.api.repository.course;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.CourseTag;
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

    @Test
    public void save() {
        // given
        Course course = new Course(2, 10, "좋은 코스입니다.", MemberRoleType.ROLE_USER);
        Tag tag = tagRepository.findById(1L).get();
        course.addCourseTag(CourseTag.create(tag));
//        course.addCoursePlace(CoursePlace.create());
        //when
        //then
    }
}