package com.iscourse.api.domain.chatbot;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Message extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Lob
    private String message;
}
