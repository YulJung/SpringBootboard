package com.icia.board.board;


import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.CommentSaveDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import com.icia.board.repository.CommentRepository;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService bs;
    @Autowired
    private BoardRepository br;
    @Autowired
    private CommentRepository cr;
    @Autowired
    private CommentService cs;
    @Test
    @Rollback
    @Transactional
    public void saveTest(){
        BoardSaveDTO boardSaveDTO = new BoardSaveDTO();
        boardSaveDTO.setBoardContents("test");
        boardSaveDTO.setBoardTitle("test");
        System.out.println(boardSaveDTO.toString());
        bs.save(boardSaveDTO);
        System.out.println(br.findById(1L));


    }
    @Test
    public void newBoard(){
        BoardSaveDTO Board = new BoardSaveDTO();
        IntStream.range(1,30).forEach(i ->
                bs.save(new BoardSaveDTO("writer"+i,"pass"+i,"title"+i,"contents"+i)));
    }
    @Test
    @DisplayName("test")
    public void test1(){
        int num = 10;
        int num2 = 0;
        if(num==10) num2 = 5;
        else num2= 100;
    }
    @Test
    @Transactional
    @DisplayName("페이징테스트")
    public void pagingTest(){
        int page= 0;
        Page<BoardEntity> board = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"boardId")));
        //page객체가 제공하는 메서드 확인
        System.out.println("board.getContent() = " + board.getContent());
        System.out.println("board.getTotalElements() = " + board.getTotalElements());
        System.out.println("board.hasPrevious() = " + board.hasPrevious());
        System.out.println("board.isFirst() = " + board.isFirst());
        System.out.println("board.isLast() = " + board.isLast());
        System.out.println("board.getTotalPages() = " + board.getTotalPages());
        System.out.println("board.getNumber() = " + board.getNumber()); //요청페이지
        //map : 엔티티가 담긴 페이지 객체를 dto가 담긴 페이지 객체로 변환해주는 역할
        Page<BoardPagingDTO> boardList = board.map(
                board1 -> new BoardPagingDTO(board1.getBoardId(),
                        board1.getBoardWriter(),
                        board1.getBoardTitle())
                );
        System.out.println("board.getContent() = " + board.getContent());
        System.out.println("board.getTotalElements() = " + board.getTotalElements());
        System.out.println("board.hasPrevious() = " + board.hasPrevious());
        System.out.println("board.isFirst() = " + board.isFirst());
        System.out.println("board.isLast() = " + board.isLast());
        System.out.println("board.getTotalPages() = " + board.getTotalPages());
        System.out.println("board.getNumber() = " + board.getNumber()); //요청페이지



    }

    //댓글작성 테스트 코드
    @Test
    @DisplayName("댓글작성 코드")
    @Transactional
    @Rollback
    public void  saveComment(){
        //글작성
        BoardSaveDTO boardSaveDTO = new BoardSaveDTO();
        boardSaveDTO.setBoardContents("test");
        boardSaveDTO.setBoardTitle("test11111");
        boardSaveDTO.setBoardPassword("test1111");
        boardSaveDTO.setBoardWriter("testWriter");
        System.out.println(boardSaveDTO.toString());
       Long id = bs.save(boardSaveDTO);
        System.out.println(br.findById(1L).toString());
        //댓글작성
        CommentSaveDTO commentSaveDTO = new CommentSaveDTO();
        commentSaveDTO.setBoardId(id);
        commentSaveDTO.setCommentContents("댓글1");
        commentSaveDTO.setCommentWriter("작성자1");
        cs.save(commentSaveDTO);

        //작성된 댓글출력
        System.out.println(commentSaveDTO.toString());
    }
}
