package com.mbti.application;


import com.mbti.domain.entity.Board;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.BoardRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.presentation.dto.BoardDto;
import com.mbti.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
        return ;
    }


    // 게시글 전체조회
    public List<BoardDto.boardDetailResponseDto>boardSearchAll() {


        //findall로 리스트에 담아줌
        List<Board> findallboard = boardRepository.findAll();

        //리스트에 담은 값을 responsedto에 넣어줘야함

        List<BoardDto.boardDetailResponseDto> boardlist = findallboard.stream()
                .map(m -> new BoardDto.boardDetailResponseDto(m.getArticleId(),m.getArticleTitle(),m.getArticleContent(),
                        m.getRegDate(),m.getArticleType(),m.getArticleWriter()))
                .collect(Collectors.toList());

        return  boardlist;

    }
}
