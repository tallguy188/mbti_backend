package com.mbti.presentation.controller;

import com.mbti.domain.repository.ChatRoomRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Tag(name="STOMP",description = "STOMP 관리 api입니다.")
@Log4j2
public class RoomController {   // 채팅화면을 보여주기 위한 컨트롤러
    private final ChatRoomRepository chatRoomRepository;

    //채팅방 목록 조회
    @Operation(summary = "채팅방 목록 조회", description = "채팅방 목록 조회 메소드입니다.")
    @GetMapping(value = "/rooms")
    public ModelAndView rooms() {      // ModelAndView = 모델과 뷰 정보를 함께 담을 수 있는 객체, 컨트롤러에서 처리한 데이터와 뷰 정보 반환
        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");
        // "chat/rooms"라는 뷰를 생성하고, ModelAndView 객체를 반환
        mv.addObject("list",chatRoomRepository.findAllRooms());
        return mv;
    }
    // 채팅방 개설
    @Operation(summary = "채팅방 개설 메소드", description = "채팅방 개설 메소드입니다.")
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr) {


        log.info("# Create Chat Room, name:" + name);
        rttr.addFlashAttribute("roomName", chatRoomRepository.createChatRoomDto(name));
        return "redirect:/chat/rooms";
    }

    @Operation(summary = "채팅방 조회 메소드", description = "채팅방 조회 메소드입니다.")
    @GetMapping("/room")
    public void getRoom(String roomId, Model model){

        log.info("# Get Chat Room, roomID : " + roomId);
        model.addAttribute("room",chatRoomRepository.findRoomById(roomId));
    }
}
