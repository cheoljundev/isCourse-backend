package com.iscourse.api.domain.dto;

import com.iscourse.api.domain.UploadFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UploadFileDto {
    private String originalFileName;
    private String storedFileName;
    private String fileType;

    @QueryProjection
    public UploadFileDto(String originalFileName, String storedFileName, String fileType) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileType = fileType;
    }
}
