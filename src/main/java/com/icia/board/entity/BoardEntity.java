package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long boardId;
    @Column
    private String boardWriter;
    @Column
    private String boardTitle;
    @Column
    private String boardPassword;
    @Column
    private String boardContents;
    @Column
    private LocalDateTime boardDate;


    public static BoardEntity saveBoard(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardDate(boardSaveDTO.getBoardDate());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardPassword(boardSaveDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        return boardEntity;
    }
}
