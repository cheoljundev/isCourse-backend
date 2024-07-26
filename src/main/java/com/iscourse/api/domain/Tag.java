package com.iscourse.api.domain;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity @Getter
public class Tag extends BaseEntity implements Persistable<TagType> {

    @Id
    @Column(name = "tag_id")
    @Enumerated(EnumType.STRING)
    private TagType id;
    private String name;

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
