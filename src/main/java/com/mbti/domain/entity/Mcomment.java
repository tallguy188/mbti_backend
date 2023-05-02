package com.mbti.domain.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="tb_mcomment")
@Getter
@Setter
@NoArgsConstructor
public class Mcomment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="mcom_id", nullable = false)
    private Integer mcomId;

    @Column(name="mcom_content")
    private String mcomContent;

    @Column(name="mcomapi_id")
    private Integer movieapiId;


    @Builder
    public Mcomment(Integer mcomId, String mcomContent, Integer movieapiId, User user) {
        this.mcomId = mcomId;
        this.mcomContent=mcomContent;
        this.movieapiId = movieapiId;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



}
