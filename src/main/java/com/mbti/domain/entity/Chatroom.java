package com.mbti.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_chatroom")
@Builder
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="chat_id", nullable = false)   //채팅방 id
    private Integer chatId;

    @ManyToMany
    @JoinTable(name = "chat_user",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> chatUser;   // 채팅방 참가자 목록


    @OneToMany(mappedBy = "chat",cascade = CascadeType.ALL)
    private List<Message> messages;

}
