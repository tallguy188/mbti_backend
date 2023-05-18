package com.mbti.domain.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name="tb_message")

public class Message {   // 별도로 메시지를 저장하거나 삭제하지 않으니까 id가 필요없다.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;   // 송신자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    private User receiver;   // 수신자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chat_id")
    private Chatroom chatroom;

}
