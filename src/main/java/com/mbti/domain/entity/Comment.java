package com.mbti.domain.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tb_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="com_id", nullable = false)
    private Integer comId;

    @Column(name="com_content", nullable = false)
    private String comContent;

    @Column(name="com_regdate", nullable = false)
    private String comRegdate;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Board board;
}
