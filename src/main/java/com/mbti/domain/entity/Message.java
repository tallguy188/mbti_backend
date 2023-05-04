package com.mbti.domain.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="tb_message")

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="message_id", nullable = false)   //메시지 id
    private Integer messageId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;   // 송신자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    private User receiver;   // 수신자

}
