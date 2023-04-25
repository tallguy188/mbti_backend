package com.mbti.presentation.controller;


import com.mbti.application.BoardService;
import com.mbti.application.CommentService;
import com.mbti.presentation.dto.CommentDto;
import com.mbti.presentation.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="게시글댓글관리",description = "게시글 댓글 관리 api입니다.")

public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @Operation(summary = "댓글 저장 메소드",description = "댓글 저장 메소드입니다.")
    @PostMapping("/board/{id}/boardcomment")
    public ResponseEntity<Response<CommentDto.CommentSaveResponseDto>>commentSave(@PathVariable Integer id, @RequestBody CommentDto.CommentSaveRequestDto commentSaveRequestDto) {


        CommentDto.CommentSaveResponseDto comment = commentService.commentSave(id, commentSaveRequestDto);

        return ResponseEntity.ok().body(Response.success(new CommentDto.CommentSaveResponseDto(comment.getId(), comment.getWriter())));

    }




}
