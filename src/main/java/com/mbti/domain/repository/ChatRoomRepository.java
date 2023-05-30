package com.mbti.domain.repository;

import com.mbti.presentation.dto.ChatRoomDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoomDto> chatRoomDtoMap;  //String 타입의 키로 ChatRoomDto 타입의 값을 저장하기 위한 맵변수 선언

    @PostConstruct  // 빈이 생성된 후 자동으로 호출
    private void init() {
        chatRoomDtoMap = new LinkedHashMap<>(); // ChatRoomDto를 저장하기 위한 map 초기화
        // LinkedMap은 요소들의 순서가 유지되는 맵, 이후 chatRoomDtoMap은 새로운 ChatRoomDto객체들을 저장하거나 검색하는 작업에 사용
    }


    // 현재 저장된 모든 채팅방 반환
    public List<ChatRoomDto> findAllRooms() {
        // 채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(result);  // 채팅방 생성 순서 최근으로 정렬, LinkedHashMap은 요소들을 입력된 순서대로 유지, 따라서 재정렬 필요
        return result;
    }

    // id로 방 찾기
    public ChatRoomDto findRoomById(String id) {
        return chatRoomDtoMap.get(id);
    }

    public ChatRoomDto createChatRoomDto(String name) {
        ChatRoomDto room = ChatRoomDto.create(name);
        chatRoomDtoMap.put(room.getRoomId(), room);

        return room;

    }

}
