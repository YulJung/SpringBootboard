package com.icia.board.dto;

import com.icia.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    private  Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity){
        BoardDetailDTO board = new BoardDetailDTO();
        board.setBoardId(boardEntity.getBoardId());
        board.setBoardContents(boardEntity.getBoardContents());
        board.setBoardTitle(boardEntity.getBoardTitle());
        board.setBoardPassword(boardEntity.getBoardPassword());
        board.setBoardWriter(boardEntity.getBoardWriter());
//        board.setBoardDate(boardEntity.getBoardDate());
        return board;

    }
}
