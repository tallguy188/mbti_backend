package com.mbti.domain.repository;


import com.mbti.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    // articleid로 게시물 찾기
    List<Comment> findCommentsByBoard_ArticleId(Integer articleid);
}

