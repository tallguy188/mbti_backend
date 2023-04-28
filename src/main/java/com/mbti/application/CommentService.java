package com.mbti.application;


import com.mbti.common.exception.BoardCommentException;
import com.mbti.common.exception.ErrorCode;
import com.mbti.common.exception.ResourceNotFoundException;
import com.mbti.domain.entity.Board;
import com.mbti.domain.entity.Comment;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.BoardRepository;
import com.mbti.domain.repository.CommentRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.presentation.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    // 게시물 댓글 저장
    public CommentDto.commentSaveResponseDto commentSave(Integer id, CommentDto.commentSaveRequestDto commentSaveRequestDto) {


        Optional<User> user = userRepository.findByUserNick(commentSaveRequestDto.getWriter());
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        commentSaveRequestDto.setUser(user);
        commentSaveRequestDto.setBoard(board);

        Comment comment = commentRepository.save(
                Comment.builder()
                        .comContent(commentSaveRequestDto.getContent())
                        .comRegdate(commentSaveRequestDto.getRegdate())
                        .comWriter(commentSaveRequestDto.getWriter())
                        .board(commentSaveRequestDto.getBoard())
                        .build());
        return CommentDto.commentSaveResponseDto.builder().id(comment.getComId())
                .writer(comment.getComWriter())
                .articleid(id)
                .build();

    }
    // 댓글 삭제
    public void commentDelete(Integer id, Integer commentId){


        Comment comment =commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        commentRepository.deleteById(commentId);

        return ;
    }

    //댓글 전체조회
    public List<CommentDto.commentDetailResponseDto>commentSearchAll(Integer articleid) {

        List<Comment> findallcomment  = commentRepository.findCommentsByBoard_ArticleId(articleid);

        List<CommentDto.commentDetailResponseDto> commentlist = findallcomment.stream()
                .map(m-> new CommentDto.commentDetailResponseDto(m.getComId(),m.getComContent(),m.getComRegdate(),m.getComWriter()))
                .collect(Collectors.toList());
        return commentlist;
    }

    //댓글 수정

    public CommentDto.commentUpdateResponseDto commentUpdate(Integer id, Integer commentid, CommentDto.commentUpdateRequestDto commentUpdateRequestDto) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board","id",id));

        Comment comment  = commentRepository.findById(commentid).orElseThrow(()-> new ResourceNotFoundException("Comment","commentid",commentid));

//        if(!comment.getBoard().getArticleId().equals(board.getArticleId())) {
//            throw new BoardCommentException(ErrorCode.NOT_FOUND,"해당 게시물의 댓글이 아닙니다.");
//        }

        comment.setComContent(commentUpdateRequestDto.getContent());
        return CommentDto.commentUpdateResponseDto.builder()
                .commentid(comment.getComId())
                .content(comment.getComContent())
                .build();

    }

}
