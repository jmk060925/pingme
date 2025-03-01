package com.pingme.domain.chat.dto;

import java.util.ArrayList;
import java.util.List;

import com.pingme.domain.chat.entity.ChatMessage;

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
public class ChatMessageDTO {

    private Long seq;

    private Long msgId;

    private String sender;

    private String content;

    private int readYn;


    public static List<ChatMessageDTO> fromEntityList(List<ChatMessage> entityList){

        List<ChatMessageDTO> result = new ArrayList<ChatMessageDTO>();

        for(ChatMessage entity : entityList){
            result.add(ChatMessageDTO.builder().seq(entity.getSeq()).msgId(entity.getMsgId()).sender(entity.getSender()).content(entity.getSender()).readYn(entity.getReadYn()).build());
        }

        return result;
    }

    public static ChatMessageDTO fromEntity(ChatMessage entity){
        return ChatMessageDTO.builder().seq(entity.getSeq()).msgId(entity.getMsgId()).sender(entity.getSender()).content(entity.getSender()).readYn(entity.getReadYn()).build();
    }

    public ChatMessage toEntity(){
        return ChatMessage.builder().seq(this.seq).msgId(this.msgId).sender(this.sender).content(this.content).readYn(this.readYn).build();
    }
}
