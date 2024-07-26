package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity @Getter
public class middleCategory extends BaseTimeEntity implements Persistable<String> {
    @Id
    @Column(name = "middle_category_id")
    private String id;
    private String name;

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
