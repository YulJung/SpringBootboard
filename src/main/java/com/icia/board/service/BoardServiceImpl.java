package com.icia.board.service;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    @Override
    public void save(BoardSaveDTO boardSaveDTO) {
        boardSaveDTO.setBoardDate(java.time.LocalDateTime.now());
        System.out.println(boardSaveDTO.toString()+"BS dto");
        BoardEntity boardEntity = BoardEntity.saveBoard(boardSaveDTO);
        System.out.println(boardEntity.toString()+"BS entity");
        br.save(boardEntity);

    }

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> list = br.findAll();
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for(BoardEntity e : list){
            boardDetailDTOList.add(BoardDetailDTO.toBoardDetailDTO(e));
        }
        return boardDetailDTOList;
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        /*
        optional
        -isPresent : 데이터가 있으면 트루 없으면 false
        -isEmpty :없으면 true
        -get : 들어있는 실제 데이터를 가져옴
         */
        BoardDetailDTO boardDetailDTO = null;
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        }
        return boardDetailDTO;
    }
}
