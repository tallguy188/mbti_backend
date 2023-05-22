package com.mbti.domain.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MissingRequestCookieException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
@Entity
@NoArgsConstructor
@Table(name="tb_user")
@Getter
@Setter
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name="user_pw", nullable = false)
    private String userPw;

    @Column(name="nick",unique = true)
    private String userNick;

    @Column(name="user_mbti")
    private String userMbti;

    @Column(name = "is_logged_in")
    private boolean isLoggedIn;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Mcomment> mcomments;

    @Builder
    public User(Integer userId,String userPw, String userNick, String userMbti) {

        this.userId = userId;
        this.userPw  = userPw;
        this.userNick = userNick;
        this.userMbti = userMbti;
    }
}
