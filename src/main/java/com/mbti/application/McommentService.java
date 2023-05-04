package com.mbti.application;


import com.mbti.common.exception.ResourceNotFoundException;
import com.mbti.domain.entity.Mcomment;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.McommentRepository;
import com.mbti.domain.repository.UserRepository;
import com.mbti.presentation.dto.McommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class McommentService {

    private final McommentRepository mcommentRepository;
    private final UserRepository userRepository;

    public void mcommentSave(Integer movieapiId, McommentDto.requestDto requestDto) {

        User comuser = userRepository.findByUserNick(requestDto.getWriter()).orElseThrow(()-> new IllegalArgumentException("해당 페이지 이용이 제한됩니다."));
        Mcomment mcomment = mcommentRepository.save(
                Mcomment.builder()
                        .mcomContent(requestDto.getMcommentContent())
                        .user(comuser)
                        .movieapiId(movieapiId)
                        .build());
        return ;
    }

    public void mcommentDelete(Integer movieapiId, Integer mcommentid) {

        Mcomment mcomment  = mcommentRepository.findById(mcommentid).orElseThrow(()->new IllegalArgumentException("해당 댓글 삭제가 불가능합니다."));
        mcommentRepository.deleteById(mcommentid);
        return ;
    }





}
