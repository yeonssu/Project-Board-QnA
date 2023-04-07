package com.qnaserver.mapper;

import com.qnaserver.dto.ReplyDto;
import com.qnaserver.entity.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {
    public Reply replyPostToReply(ReplyDto.Post replyPostDto) {
        Reply reply = new Reply();

        reply.setContent(replyPostDto.getContent());

         return reply;
    }

}
