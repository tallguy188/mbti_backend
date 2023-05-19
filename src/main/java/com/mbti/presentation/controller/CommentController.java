package com.mbti.presentation.controller;



import com.mbti.application.CommentService;

import com.mbti.presentation.dto.CommentDto;
import com.mbti.presentation.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "게시글댓글관리", description = "게시글 댓글 관리 api입니다.")

public class CommentController {


    private final CommentService commentService;

    @Operation(summary = "댓글 저장 메소드", description = "댓글 저장 메소드입니다.")
    @PostMapping("/board/{id}/boardcomment")
    public ResponseEntity<Response<CommentDto.commentSaveResponseDto>> commentSave(@PathVariable Integer id, @RequestBody CommentDto.commentSaveRequestDto commentSaveRequestDto) {

        CommentDto.commentSaveResponseDto comment = commentService.commentSave(id, commentSaveRequestDto);

        return ResponseEntity.ok().body(Response.success(new CommentDto.commentSaveResponseDto(comment.getId(), comment.getWriter(), comment.getArticleid())));

    }

    @Operation(summary = "댓글 삭제 메소드", description = "댓글 삭제 메소드입니다.")
    @DeleteMapping("/board/{id}/boardcomment/{commentid}")   // 여기서 id는 article_id, commentid는 댓글 id
    public ResponseEntity<Response<HttpStatus>> commentDelete(@PathVariable Integer id, @PathVariable Integer commentid) {

        commentService.commentDelete(id, commentid);

        return ResponseEntity.ok().body(Response.success(HttpStatus.OK));
    }

    @Operation(summary = "게시물 댓글 전체 조회 메소드", description = "댓글 전체 조회 메소드입니다.")
    @GetMapping("/board/{id}/boardcomment")
    public List<CommentDto.commentDetailResponseDto> commentSearchAll(@PathVariable Integer id) {
        return commentService.commentSearchAll(id);
    }

    @Operation(summary = "댓글 수정 메소드", description = "댓글 수정 메소드입니다.")
    @PutMapping("/board/{id}/boardcomment/{commentid}")
    public CommentDto.commentUpdateResponseDto commentUpdate(@PathVariable Integer id, @PathVariable Integer commentid, @RequestBody CommentDto.commentUpdateRequestDto commentUpdateRequestDto) {


        CommentDto.commentUpdateResponseDto comment = commentService.commentUpdate(id,commentid,commentUpdateRequestDto);

        return new CommentDto.commentUpdateResponseDto(comment.getCommentid(),comment.getContent());
    }

}
