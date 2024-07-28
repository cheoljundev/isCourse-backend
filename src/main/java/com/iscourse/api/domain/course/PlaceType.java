package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceType{
    @Id @GeneratedValue
    @Column(name = "place_type_id")
    private Long id;
    private String code;
    private String name;

    public PlaceType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
