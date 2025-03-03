package com.pingme.api;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.domain.chat.dto.ChatMessageDTO;
import com.pingme.domain.chat.dto.ChatRoomDTO;
import com.pingme.domain.chat.service.ChatMessageService;
import com.pingme.domain.chat.service.ChatRoomService;
import com.pingme.infrastructure.dto.ResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;


    @PostMapping("/room/list")
    public List<ChatRoomDTO> retrieveChatRoomList(@RequestBody ChatRoomDTO request){
        return chatRoomService.retrieveChatRoom(request);
    }

    @PostMapping("/room/list/lastmsg")
    public ChatMessageDTO retrieveLastMessage(@RequestBody ChatRoomDTO request){
        return chatRoomService.retrieveLastMessage(request);
    }

    
    @PostMapping("/room/list/unreadcnt")
    public Long getUnreadCount(@RequestBody ChatRoomDTO request){
        return chatRoomService.getUnreadCount(request);
    }

    @PostMapping("/message/list")
    public List<ChatMessageDTO> retrieveMessageList(@RequestBody ChatMessageDTO request){
        
        return chatMessageService.retrieveMessageList(request);
    }

    @PostMapping("/message/send")
    public ResponseDTO createMessage(@RequestBody ChatMessageDTO request, Authentication authentication){
        String username = authentication.getName();
        return chatMessageService.createMessage(request, username);
    }

    @PostMapping("/message/receive")
    public ResponseDTO readMessage(@RequestBody ChatMessageDTO request){
        return chatMessageService.readMessage(request);
    }
}
