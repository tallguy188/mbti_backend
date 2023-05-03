package com.mbti.presentation.controller;



import com.mbti.application.McommentService;
import com.mbti.domain.entity.Mcomment;
import com.mbti.domain.repository.McommentRepository;
import com.mbti.presentation.dto.McommentDto;
import com.mbti.presentation.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "영화추천댓글관리", description = "영화추천 댓글 관리 api입니다.")
public class McommentController {

    private final McommentService mcommentService;

    @Operation(summary = "영화추천 댓글저장 메소드", description = "영화추천 댓글 저장 메소드입니다.")
    @PostMapping("/movierecommendation/{movieapiId}/moviecomment")
    public ResponseEntity<Response<HttpStatus>>mcommentSave(@PathVariable Integer movieapiId, @RequestBody McommentDto.requestDto requestDto) {

        mcommentService.mcommentSave(movieapiId,requestDto);

        return ResponseEntity.ok().body(Response.success(HttpStatus.OK));
    }

    @Operation(summary = "영화추천 댓글삭제 메소드", description = "영화추천 댓글 삭제 메소드입니다.")
    @DeleteMapping("/movierecommendation/{movieapiId}/moviecomment/{mcommentid}")
    public ResponseEntity<Response<HttpStatus>>mcommentDelete(@PathVariable Integer movieapiId, @PathVariable Integer mcommentid) {

       mcommentService.mcommentDelete();
    }


}
