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
    public UploadFileDto(UploadFile uploadFile) {
        this.originalFileName = uploadFile.getOriginalFileName();
        this.storedFileName = uploadFile.getStoredFileName();
        this.fileType = uploadFile.getFileType();
    }
}
