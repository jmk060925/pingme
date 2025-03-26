package com.pingme.domain.chat.service;

import java.util.List;
import java.util.Random;

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

    // //myRoomList
    // public List<ChatRoomDTO> retrieveChatRoom(ChatRoomDTO request){
    //     return ChatRoomDTO.fromEntityList(chatRoomRepository.findById(request.getMsgId()));
    // } 
    
    
    public ChatRoomDTO retrieveChatRoom(ChatRoomDTO request){
        return ChatRoomDTO.fromEntity(chatRoomRepository.findByUsername1AndUsername2(request.getUsername1(), request.getUsername2()).get());
    }

    //create
    public ResponseDTO createChatRoom(ChatRoomDTO request){

        if(retrieveChatRoom(request) != null || chatRoomRepository.findByUsername1AndUsername2(request.getUsername2(), request.getUsername1()).isEmpty()){
            return ResponseDTO.builder().resultcode("F").msg("The Chatroom is already exist.").build();
        }

        //msgId 채번
        Random random = new Random();
        long msgId = random.nextLong();
        request.setMsgId(msgId);

        chatRoomRepository.save(request.toEntity());
        return ResponseDTO.builder().resultcode("S").msg(Long.toString(request.getMsgId())).build();
    }

    //delete, 나중에 사용
    public ResponseDTO deleteChatRoom(ChatRoomDTO request){
        //chatRoomRepository.deleteById(request.getMsgId());
        return ResponseDTO.builder().resultcode("S").build();
    }
    
}
