package com.mbti.domain.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tb_movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id", nullable = false)
    private Integer movieapiId;

    @Column(name="movie_url")
    private String movieUrl;

    @Column(name="img_url")
    private String imgUrl;

    @Column(name="movie_title", nullable = false)
    private String movieTitle;

    @Column(name="movie_year")
    private Date movieYear;

    @Column(name="movie_dir")
    private String movieDir;

    @Column(name="movie_rate")
    private Integer movieRate;

    @Column(name="movie_act")
    private String movieAct;

    @Column(name="movie_reg")
    private Date movieReg;

}
