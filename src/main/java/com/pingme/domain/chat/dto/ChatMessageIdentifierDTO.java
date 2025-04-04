package com.pingme.domain.chat.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessageIdentifierDTO  implements Serializable{

    private Long seq;

    private Long msgId;

    private String sender;

}
