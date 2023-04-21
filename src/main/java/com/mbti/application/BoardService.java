package com.mbti.application;


import com.mbti.domain.entity.Board;
import com.mbti.domain.repository.BoardRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.presentation.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;



    public BoardDto.BoardResponseDto postsave(BoardDto.BoardRequestDto boardRequestDto) {

        Board board = boardRepository.save(
                Board.builder()
                        .articleTitle(boardRequestDto.getContent())
                        .articleContent(boardRequestDto.getContent())
                        .regDate(boardRequestDto.getRegdate())
                        .articleType(boardRequestDto.getMbti())
                        .articleWriter(boardRequestDto.getNick())
                        .build());



        return BoardDto.BoardResponseDto.builder().id(board.getArticleId()).build();

    }

}
