package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.domain.Persistable;

@Entity
public class smallCategory extends BaseTimeEntity implements Persistable<String> {
    @Id
    @Column(name = "small_category_id")
    private String id;
    private String name;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
