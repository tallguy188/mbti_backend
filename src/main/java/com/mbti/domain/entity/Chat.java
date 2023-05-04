package com.mbti.domain.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="tb_chat")

public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="chat_id", nullable = false)   //채팅방 id
    private Integer chatId;

    @ManyToMany
    @JoinTable(name = "chat_user",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> chatUser;   // 채팅방 참가자 목록
}
