package com.iscourse.api.repository.course;

import com.iscourse.api.domain.course.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCode(String code);
}
