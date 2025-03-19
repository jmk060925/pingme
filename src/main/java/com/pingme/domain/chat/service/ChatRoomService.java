package com.pingme.domain.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.domain.chat.dto.ChatMessageDTO;
import com.pingme.domain.chat.dto.ChatRoomDTO;
import com.pingme.domain.chat.repository.ChatMessageRepository;
import com.pingme.domain.chat.repository.ChatRoomRepository;
import com.pingme.infrastructure.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatRoomService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    //myRoomList
    public List<ChatRoomDTO> retrieveChatRoom(ChatRoomDTO request){
        return ChatRoomDTO.fromEntityList(chatRoomRepository.findByUsername(request.getUsername()));
    }

    //lastMsg, 나중에 사용용
    public ChatMessageDTO retrieveLastMessage(ChatRoomDTO request){
        return ChatMessageDTO.fromEntity(chatMessageRepository.findFirstByMsgIdOrderBySeqDesc(request.getMsgId()).get());
    }

    //create
    public ResponseDTO createChatRoom(ChatRoomDTO request){
        //msgId 채번
        chatRoomRepository.save(request.toEntity());
        return ResponseDTO.builder().resultcode("S").msg(Long.toString(request.getMsgId())).build() ;
    }

    //delete, 나중에 사용
    public ResponseDTO deleteChatRoom(ChatRoomDTO request){
        //chatRoomRepository.deleteById(request.getMsgId());
        return ResponseDTO.builder().resultcode("S").build();
    }
    
}
