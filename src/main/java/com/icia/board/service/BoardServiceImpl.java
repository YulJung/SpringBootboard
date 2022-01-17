package com.icia.board.service;

import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        boardSaveDTO.setBoardDate(java.time.LocalDateTime.now());
        System.out.println(boardSaveDTO.toString()+"BS dto");
        BoardEntity boardEntity = BoardEntity.saveBoard(boardSaveDTO);
        System.out.println(boardEntity.toString()+"BS entity");


        return br.save(boardEntity).getBoardId();
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

    @Override
    public Long update(BoardUpdateDTO  boardSaveDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateBoard(boardSaveDTO);
        Long boardId = br.save(boardEntity).getBoardId();
        return boardId;
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); //페이지번호가져옴
        //요청한 페이지가 1이면 페이지값을 0으로 만들고, 1이아니면 요청페이지에서 1을뺀다
//        page = page-1;
        page = (page==1)? 0:(page-1);
        Page<BoardEntity> board= br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"id")));

        Page<BoardPagingDTO> boardList = board.map(
                board1 -> new BoardPagingDTO(board1.getBoardId(),
                        board1.getBoardWriter(),
                        board1.getBoardTitle())
        );
        return null;

    }
}
