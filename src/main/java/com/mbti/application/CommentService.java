package com.mbti.application;


import com.mbti.domain.entity.Board;
import com.mbti.domain.entity.Comment;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.BoardRepository;
import com.mbti.domain.repository.CommentRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.presentation.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    // 게시물 댓글 저장
    public CommentDto.CommentSaveResponseDto commentSave(Integer id, CommentDto.CommentSaveRequestDto commentSaveRequestDto) {


        Optional<User> user = userRepository.findByUserNick(commentSaveRequestDto.getWriter());


        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.save(
                Comment.builder()
                        .comContent(commentSaveRequestDto.getContent())
                        .comRegdate(commentSaveRequestDto.getRegdate())
                        .comWriter(commentSaveRequestDto.getWriter())
                        .build());


        return CommentDto.CommentSaveResponseDto.builder().id(comment.getComId())
                .writer(comment.getComWriter()).build();


    }

}
