package com.mbti.application;


import com.mbti.domain.entity.Board;
import com.mbti.domain.repository.BoardRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.presentation.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;
    // 게시글 저장
    public BoardDto.boardSaveResponseDto boardSave(BoardDto.boardSaveRequestDto boardSaveRequestDto) {
        Board board = boardRepository.save(
                Board.builder()
                        .articleTitle(boardSaveRequestDto.getContent())
                        .articleContent(boardSaveRequestDto.getContent())
                        .regDate(boardSaveRequestDto.getRegdate())
                        .articleType(boardSaveRequestDto.getMbti())
                        .articleWriter(boardSaveRequestDto.getNick())
                        .build());
        return BoardDto.boardSaveResponseDto.builder().id(board.getArticleId()).build();
    }

    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);

        return ;
    }

}
