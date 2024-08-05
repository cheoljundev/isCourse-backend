package com.iscourse.api.service;

import com.iscourse.api.domain.RelatedType;
import com.iscourse.api.domain.UploadFile;
import com.iscourse.api.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadFileService {
    private final UploadFileRepository uploadFileRepository;
    String projectPath = System.getProperty("user.dir");
    @Value("${file.dir}") String fileDir;

    @Transactional
    public void save(MultipartFile file, RelatedType relatedType, Long relatedId) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String storedFileName = UUID.randomUUID().toString() + "." + fileType;

        // DB에 파일 정보 저장
        UploadFile uploadFile = new UploadFile(originalFileName, storedFileName, fileType, relatedType, relatedId);
        uploadFileRepository.save(uploadFile);

        // 파일 저장 경로
        String filePath = projectPath + fileDir + relatedType.getPath() + storedFileName;

        // 파일 디렉토리가 없으면 생성
        File directory = new File(projectPath + fileDir + relatedType.getPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        file.transferTo(new File(filePath));
    }

    @Transactional
    public void delete(Long id) {
        UploadFile uploadFile = uploadFileRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        uploadFile.delete();
    }
}
