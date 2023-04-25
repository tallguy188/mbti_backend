package com.mbti.domain.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tb_comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="com_id", nullable = false)
    private Integer comId;

    @Column(name="com_content")
    private String comContent;

    @Column(name="com_regdate")
    private String comRegdate;

    @Column(name="com_writer")
    private String comWriter;

    @Builder
    public Comment(Integer comId, String comContent, String comRegdate, String comWriter, Board board){

        this.comId = comId;
        this.comContent = comContent;
        this.comRegdate = comRegdate;
        this.comWriter = comWriter;
        this.board = board;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Board board;
}
