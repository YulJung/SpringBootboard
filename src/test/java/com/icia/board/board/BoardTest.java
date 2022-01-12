package com.icia.board.board;


import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.repository.BoardRepository;
import com.icia.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    @Rollback
    @Transactional
    public void saveTest(){
        BoardSaveDTO boardSaveDTO = new BoardSaveDTO();
        boardSaveDTO.setBoardContents("test");
        boardSaveDTO.setBoardTitle("test");
        System.out.println(boardSaveDTO.toString());
        bs.save(boardSaveDTO);
        System.out.println(br.findById(1L).toString());


    }
    @Test
    public void newBoard(){
        BoardSaveDTO Board = new BoardSaveDTO();
        IntStream.range(1,30).forEach(i ->
                bs.save(new BoardSaveDTO("writer"+i,"pass"+i,"title"+i,"contents"+i)));
    }

}
