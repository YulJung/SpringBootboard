package com.icia.board.dto;

import com.icia.board.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data

public class CommentDetailDTO {
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private Long commentId;
    private LocalDateTime createTime;
    public static CommentDetailDTO toCommentDetailDTO(CommentEntity comment) {
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setCommentContents(comment.getCommentContents());
        commentDetailDTO.setCommentWriter(comment.getCommentWriter());
        commentDetailDTO.setBoardId(comment.getBoardEntity().getBoardId());
        commentDetailDTO.setCommentId(comment.getId());
        commentDetailDTO.setCreateTime(comment.getCreateTime());
        return commentDetailDTO;
    }
}
