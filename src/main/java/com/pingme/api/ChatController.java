package com.pingme.api;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final SimpMessagingTemplate messagingTemplate;

    private final SimpMessageSendingOperations  sendingOperations;

    @PostMapping("/room/list")
    public List<ChatRoomDTO> retrieveChatRoomList(@RequestBody ChatRoomDTO request){
        return chatRoomService.retrieveChatRoom(request);
    }
    

    @PostMapping("/message/list")
    public List<ChatMessageDTO> retrieveMessageList(@RequestBody ChatMessageDTO request){
        
        return chatMessageService.retrieveMessageList(request);
    }

    @MessageMapping("/message/send")
    @SendTo("/sub/chat/message/receive/1")
    public ResponseDTO createMessage(@Payload ChatMessageDTO request){
        
        log.info(request.toString());
        //messagingTemplate.convertAndSend("/sub/chat/message/send/" + 1, request);
        return chatMessageService.createMessage(request);
    }

    @PostMapping("/message/receive")
    public ResponseDTO readMessage(ChatMessageDTO request){
        return chatMessageService.readMessage(request);
    }
}
