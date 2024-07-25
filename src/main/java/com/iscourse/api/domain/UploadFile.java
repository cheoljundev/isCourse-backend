package com.iscourse.api.domain;

import jakarta.persistence.*;

@Entity
public class UploadFile extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "upload_file_id")
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private String fileType;

    private RelatedType relatedType;
    private Long relatedId;
}