package com.iscourse.api.domain.chatbot;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
public class ChatTemplate extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "chat_template_id")
    private Long id;

    @Lob
    private String question;

    @Lob
    private String answer;
}
