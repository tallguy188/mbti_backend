package com.mbti.domain.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tb_mcomment")
public class Mcomment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="mcom_id", nullable = false)
    private Integer mcomId;

    @Column(name="mcom_content")
    private String mcomContent;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
}
