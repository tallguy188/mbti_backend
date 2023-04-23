package com.mbti.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="tb_board")

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="article_id", nullable = false)
    private Integer articleId;

    @Column(name="article_title", nullable=false)
    private String articleTitle;

    @Column(name="article_content")
    private String articleContent;

    @Column(name="regdate", nullable=false)
    private String regDate;

    @Column(name="article_type")
    private String articleType;

    @Column(name="article_writer")
    private String articleWriter;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Board( String articleTitle, String articleContent, String regDate, String articleType,String articleWriter){

        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.regDate = regDate;
        this. articleType = articleType;
        this.articleWriter = articleWriter;
    }





}
