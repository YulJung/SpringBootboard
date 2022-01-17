package com.icia.board.entity;

import com.icia.board.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;
    @Column
    private String memberEmail;
    @Column
    private String memberPassword;
    @Column
    private String memberName;
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static MemberEntity toMemberSaveEntity(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        return memberEntity;
    }
}
