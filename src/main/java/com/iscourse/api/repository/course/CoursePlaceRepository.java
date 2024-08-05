package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.course.CoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePlaceRepository extends JpaRepository<CoursePlace, Long> {
    List<CoursePlace> findByCourse(Course course);
}
