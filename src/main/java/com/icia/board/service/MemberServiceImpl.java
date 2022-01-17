package com.icia.board.service;

import com.icia.board.dto.MemberSaveDTO;
import com.icia.board.entity.MemberEntity;
import com.icia.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository mr;
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity member = MemberEntity.toMemberSaveEntity(memberSaveDTO);
        return mr.save(member).getId();
    }
}
