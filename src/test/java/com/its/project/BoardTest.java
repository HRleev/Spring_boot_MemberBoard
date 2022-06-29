package com.its.project;

import com.its.project.dto.BoardDTO;
import com.its.project.dto.MemberDTO;
import com.its.project.entity.BoardEntity;
import com.its.project.entity.MemberEntity;
import com.its.project.repository.BoardRepository;
import com.its.project.repository.MemberRepository;
import com.its.project.service.BoardService;
import com.its.project.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;



//    @Test
//    @Transactional
//    @Rollback(value = true)
//    public BoardDTO newBoard(int i) {
//        BoardDTO board = new BoardDTO( "boardTitle" + i, "boardWriter" + i,"boardContents" + i,1+i);
//        return board;
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(value = true)
//    public void newMember() {
//        MemberDTO memberDTO = new MemberDTO("email1", "pw1", "name1");
//        memberRepository.save(MemberEntity.toSaveEntity(memberDTO));
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("게시글 저장 테스트")
//    public void memberBoardSaveTest(){
//        BoardDTO boardDTO=newBoard(10);
//        MemberEntity memberEntity= memberRepository.findByMemberEmail("boardWriter").get();
//        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO,memberEntity);
//        boardRepository.save(boardEntity);
//    }


}
