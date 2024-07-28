package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Long> {
}
