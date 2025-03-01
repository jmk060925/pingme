package com.pingme.domain.chat.dto;


import java.util.ArrayList;
import java.util.List;

import com.pingme.domain.chat.entity.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO{

    private Long msgId;

    private String username1;

    private String username2;

    public static List<ChatRoomDTO> fromEntityList(List<ChatRoom> entityList){

        List<ChatRoomDTO> result = new ArrayList<ChatRoomDTO>();

        for(ChatRoom entity : entityList){
            result.add(ChatRoomDTO.builder().msgId(entity.getMsgId()).username1(entity.getUsername1()).username2(entity.getUsername2()).build());
        }

        return result;
    }

    public static ChatRoomDTO fromEntity(ChatRoom entity){
        return ChatRoomDTO.builder().msgId(entity.getMsgId()).username1(entity.getUsername1()).username2(entity.getUsername2()).build();
    }


    public ChatRoom toEntity(){
        return ChatRoom.builder().msgId(this.msgId).username1(this.username1).username2(this.username2).build();
    }
}

