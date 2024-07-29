package com.iscourse.api.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class UploadFile extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "upload_file_id")
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private String fileType;

    @Enumerated(EnumType.STRING)
    private RelatedType relatedType;
    private Long relatedId;

    public UploadFile(String originalFileName, String storedFileName, String fileType, RelatedType relatedType, Long relatedId) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
        this.relatedType = relatedType;
        this.relatedId = relatedId;
    }
}