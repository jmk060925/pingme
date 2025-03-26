package com.pingme.domain.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.pingme.domain.chat.dto.ChatMessageDTO;
import com.pingme.domain.chat.dto.ChatMessageIdentifierDTO;
import com.pingme.domain.chat.dto.ChatRoomDTO;
import com.pingme.domain.chat.entity.ChatMessage;
import com.pingme.domain.chat.repository.ChatMessageRepository;
import com.pingme.domain.chat.repository.ChatRoomRepository;
import com.pingme.infrastructure.dto.ResponseDTO;
import com.pingme.infrastructure.jwt.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired 
    private ChatRoomService chatRoomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private JwtTokenProvider tokenProvider;

    //msglist, 나중에 페이징 처리 추가
    public List<ChatMessageDTO> retrieveMessageList(ChatMessageDTO request){
        List<ChatMessage> entityList = chatMessageRepository.findByMsgIdOrderBySeqDesc(request.getMsgId());

        return ChatMessageDTO.fromEntityList(entityList);
    }


    //send
    public ResponseDTO createMessage(ChatMessageDTO request, String token){
           
        //https://star-peanuts.tistory.com/123
        //https://dev-bok.tistory.com/46

        //username으로 채팅방 찾기

        
        String username1 = tokenProvider.getAuthentication(token).getName();
        String username2 = request.getSender();

        ChatRoomDTO dto = chatRoomService.retrieveChatRoom(ChatRoomDTO.builder().username1(username1).username2(username2).build());

        if(dto == null){
            dto = chatRoomService.retrieveChatRoom(ChatRoomDTO.builder().username1(username2).username2(username1).build());
        }

        if(dto != null){
            request.setMsgId(dto.getMsgId());
        }else{
            request.setMsgId(Long.parseLong(chatRoomService.createChatRoom(ChatRoomDTO.builder().title(username1 + "," + username2).username1(username1).username2(username2).build()).getMsg()));
        }
    
        messagingTemplate.convertAndSend("/sub/chat/message/receive/" + request.getMsgId(), request);
        return ResponseDTO.builder().resultcode("S").msg(request.getContent()).build();
    }
    
}
