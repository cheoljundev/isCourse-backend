package com.iscourse.api.repository.deal;

import com.iscourse.api.domain.RelatedType;
import com.iscourse.api.domain.UploadFile;
import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.repository.UploadFileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class DealRepositoryTest {

    @Autowired DealRepository dealRepository;
    @Autowired UploadFileRepository uploadFileRepository;

    @Test
    public void save() {
        // given
        Deal deal = new Deal("삼성역", "삼성돈까스2", "삼성돈까스 자유이용권2", 10000, 5000, 50,
                "월회수목금", "토일", "서울시 강남구 테헤란로 1", "삼성빌딩 301호", "0212341234", 00000.00, 00000.00, true);
        //when
        dealRepository.save(deal);
        UploadFile uploadFile = new UploadFile("test.jpg", "test.jpg", "jpg", RelatedType.DEAL, deal.getId());
        uploadFileRepository.save(uploadFile);
        //then
    }
}