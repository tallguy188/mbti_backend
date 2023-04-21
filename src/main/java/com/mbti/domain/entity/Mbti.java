package com.mbti.domain.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tb_mbti")
public class Mbti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="mbti_id")
    private Integer mbtiId;

    @Column(name="mbti_name")
    private String mbtiName;

    @Column(name="mbti_genre")
    private Integer mbtiGenre;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


}
