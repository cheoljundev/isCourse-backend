package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.CoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePlaceRepository extends JpaRepository<CoursePlace, Long> {
}
