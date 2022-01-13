package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
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
//    @Column
//    private LocalDateTime boardDate;


    public static BoardEntity saveBoard(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = new BoardEntity();
//        boardEntity.setBoardDate(boardSaveDTO.getBoardDate());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardPassword(boardSaveDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        return boardEntity;
    }

    public static BoardEntity toUpdateBoard(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardId(boardUpdateDTO.getBoardId());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardUpdateDTO.getBoardWriter());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());

        return boardEntity;
    }
}
