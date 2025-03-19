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
import com.pingme.infrastructure.dto.ResponseDTO;

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

    //msglist, 나중에 페이징 처리 추가
    public List<ChatMessageDTO> retrieveMessageList(ChatMessageDTO request){
        List<ChatMessage> entityList = chatMessageRepository.findByMsgIdOrderBySeqDesc(request.getMsgId());

        return ChatMessageDTO.fromEntityList(entityList);
    }

    //send
    public ResponseDTO createMessage(ChatMessageDTO request){
        
        ResponseDTO result = null;
        String msgId = null;
        if(request.getMsgId() != null){
            result = chatRoomService.createChatRoom(ChatRoomDTO.builder().msgId(request.getMsgId()).username(request.getSender()).build());
            request.setMsgId(Long.parseLong(msgId));
            //chatMessageRepository.save(request.toEntity());
        }

        log.info("msgId : " + Long.parseLong(msgId));
        
        //https://star-peanuts.tistory.com/123
        //https://dev-bok.tistory.com/46
        
        

        messagingTemplate.convertAndSend("/sub/chat/message/receive/1", request);
        return ResponseDTO.builder().resultcode("S").msg(request.getContent()).build();
    }

    //read one by one, 나중에 사용
    public ResponseDTO readMessage(ChatMessageDTO request){
        ChatMessage newMsg = chatMessageRepository.findById(ChatMessageIdentifierDTO.builder().seq(request.getSeq()).msgId(request.getMsgId()).build()).get();

        newMsg.setReadYn(1);

        return ResponseDTO.builder().resultcode("S").build();
    }
    
}
