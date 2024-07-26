package com.iscourse.api.domain;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity @Getter
public class Tag extends BaseEntity implements Persistable<String> {

    @Id
    @Column(name = "tag_id")
    private String id;
    private String name;

    @Override
    public boolean isNew() {
        return false;
    }
}
