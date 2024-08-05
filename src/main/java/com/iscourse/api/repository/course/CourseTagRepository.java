package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.CourseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {
    List<CourseTag> findByCourse(Course course);
}
