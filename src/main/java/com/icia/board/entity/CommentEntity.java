package com.icia.board.entity;

import com.icia.board.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;
    //원글의 게시글 번호를 참고하기 위한 컬럼
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    // member id join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    private String commentWriter;
    @Column
    private String commentContents;

    public static CommentEntity toSaveEntity(CommentSaveDTO commentSaveDTO,BoardEntity boardEntity){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentSaveDTO.getCommentWriter());
        commentEntity.setCommentContents(commentEntity.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
