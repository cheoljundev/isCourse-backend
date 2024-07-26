package com.iscourse.api.repository;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, TagType> {
}
