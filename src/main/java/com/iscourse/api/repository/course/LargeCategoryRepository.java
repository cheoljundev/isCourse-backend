package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.LargeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeCategoryRepository extends JpaRepository<LargeCategory, Long> {
}
