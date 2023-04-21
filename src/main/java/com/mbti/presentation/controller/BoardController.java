package com.mbti.presentation.controller;


import com.mbti.application.BoardService;
import com.mbti.domain.entity.Board;
import com.mbti.presentation.dto.BoardDto;
import com.mbti.presentation.dto.Response;
import com.mbti.presentation.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;

@RestController
@RequiredArgsConstructor  // 필드생성자자동주입
@Tag(name="게시글관리",description = "게시글 관리 api입니다.")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시글 저장 메소드", description = "게시글 저장 메소드입니다.")
    @PostMapping("/boardwrite")
    public ResponseEntity<Response<BoardDto.BoardResponseDto>>postsave(@RequestBody BoardDto.BoardRequestDto boardRequestDto){


        BoardDto.BoardResponseDto board = boardService.postsave(boardRequestDto);
        return ResponseEntity.ok().body(Response.success(new BoardDto.BoardResponseDto(board.getId())));





    }

}
