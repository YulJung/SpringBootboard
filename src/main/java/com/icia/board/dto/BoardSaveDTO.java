package com.icia.board.dto;

import com.icia.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {

    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;

    public BoardSaveDTO(String writer, String password, String title, String contents) {
    }

    public static BoardSaveDTO toBoardSaveDTO(BoardEntity boardEntity){
        BoardSaveDTO board = new BoardSaveDTO();
        board.setBoardTitle(boardEntity.getBoardTitle());
        board.setBoardDate(boardEntity.getBoardDate());
        board.setBoardContents(boardEntity.getBoardContents());
        board.setBoardPassword(board.getBoardPassword());
        board.setBoardWriter(board.getBoardWriter());
        return board;
    }
}
