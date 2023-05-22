package com.mbti.application;
import com.mbti.common.config.EncrypterConfig;
import com.mbti.common.exception.UserJoinLoginException;
import com.mbti.common.exception.ErrorCode;
import com.mbti.common.exception.UserNotFoundException;
import com.mbti.common.util.JwtTokenUtil;
import com.mbti.domain.entity.User;
import com.mbti.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mbti.presentation.dto.UserDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EncrypterConfig encrypterConfig;

    private final BCryptPasswordEncoder encoder;

    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.token.secret}")
    private String secretKey;  // yml에서 설정한 token의 키값 설정
    private long expireTimeMs = 1000*60*60; // 1시간

    // 회원가입
    public UserDto.RegisterResponseDto register(UserDto.UserRequestDto userRequestDto) {


        userRepository.findByUserNick(userRequestDto.getNick()).ifPresent(user -> {
            throw new UserJoinLoginException(ErrorCode.DUPLICATE_RESOURCE,String.format("username:",userRequestDto.getNick()));
        });

        User user = userRepository.save(
                User.builder()
                        .userId(userRequestDto.getId())
                        .userNick(userRequestDto.getNick())
                        .userPw(encoder.encode(userRequestDto.getPw()))   //패스워드 암호화
                        .userMbti(userRequestDto.getMbti())
                        .build());


        return UserDto.RegisterResponseDto.builder().id(user.getUserId())
                .nick(user.getUserNick())
                .mbti(user.getUserMbti())
                .build();
    }
    // 로그인
    public UserDto.UserLoginResponse login(UserDto.UserRequestDto userRequestDto) throws UsernameNotFoundException {

        // db에서 유저이름 확인
        User user = userRepository.findByUserNick(userRequestDto.getNick())

                .orElseThrow(()-> new UserJoinLoginException(ErrorCode.NOT_FOUND,String.format("가입된적 없습니다.")));
        // 패스워드 일치 확인

        String rawPassword = userRequestDto.getPw();
        if(rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        if(!encoder.matches(rawPassword,user.getUserPw())) {
            throw new UserJoinLoginException(ErrorCode.INVALID_PASSWORD,String.format("비밀번호가 일치하지 않습니다"));
        }
        // 로그인되면 true
        user.setLoggedIn(true);
        userRepository.save(user);

        String usertoken =JwtTokenUtil.createToken(user.getUserNick(),expireTimeMs,secretKey);
        // 두가지 모두 통과하면 토큰 발행
        return UserDto.UserLoginResponse.builder().token(usertoken)
                .mbti(user.getUserMbti()).build();
    }

     // 로그아웃
    @Transactional
    public void refresh(UserDto.UserLogoutRequest request) {
        String nick = request.getNick();    //getnick이 null을 불러온다.
        System.out.println(nick);
        User user = userRepository.
                findByUserNick(request.getNick()).orElseThrow(() -> new UserNotFoundException("user not found"));
        user.setLoggedIn(false);
        userRepository.save(user);
    }


    // 사용자 id로 사용자 찾기

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    // 로그인한 사용자 리스트 반환
    public List<UserDto.UserLoginListResponse> loginSearchAll() {


        List<User> findalluser = userRepository.findByIsLoggedInIsTrue();

        List<UserDto.UserLoginListResponse> userlist = findalluser.stream()
                .map(m-> new UserDto.UserLoginListResponse(m.getUserNick(),m.getUserMbti()))
                .collect(Collectors.toList());

        return userlist;


    }
}
