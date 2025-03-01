package com.pingme.domain.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.domain.chat.dto.ChatMessageDTO;
import com.pingme.domain.chat.dto.ChatRoomDTO;
import com.pingme.domain.chat.entity.ChatRoom;
import com.pingme.domain.chat.repository.ChatMessageRepository;
import com.pingme.domain.chat.repository.ChatRoomRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatRoomService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    //myRoomList
    public List<ChatRoom> retrieveChatRoom(String request){
        
        return chatRoomRepository.findByUsername1OrUsername2(request, request);
    }

    //lastMsg
    private ChatMessageDTO retrieveLastMessage(Long msgId){
        return null;
    }

    private Long getUnreadCount(ChatRoomDTO request){
        return 0l;
    }
    
}
