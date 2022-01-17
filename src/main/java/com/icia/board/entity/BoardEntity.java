package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    //댓글 연관관계
    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

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
