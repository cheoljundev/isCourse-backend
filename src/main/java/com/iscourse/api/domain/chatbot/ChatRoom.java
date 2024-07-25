package com.iscourse.api.domain.chatbot;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.member.Member;
import jakarta.persistence.*;

@Entity
public class ChatRoom extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member user;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Member manager;


}
