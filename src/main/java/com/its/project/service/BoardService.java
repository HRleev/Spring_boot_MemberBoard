package com.its.project.service;

import com.its.project.dto.BoardDTO;
import com.its.project.entity.BoardEntity;
import com.its.project.entity.MemberEntity;
import com.its.project.repository.BoardRepository;
import com.its.project.common.PagingConst;
import com.its.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();//요청페이지값을 가져옴
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1;
        //삼항 연산자
        page = (page == 1) ? 0 : (page - 1);
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Page<BoardEntity> => Page<BoardDTO>
        Page<BoardDTO> boardList = boardEntities.map(
                //boardEntity객체->BoardDTO객체 변환
                //board:BoardEntity객체
                //new BoardDTO()생성자
                board -> new BoardDTO(board.getId(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardWriter(),
                        board.getBoardHits(),
                        board.getCreatedTime(),
                        board.getBoardFileName()
                ));
        return boardList;
    }

    public Long save(BoardDTO boardDTO) throws IOException {
        System.out.println("BoardService.save");
        MultipartFile boardFile = boardDTO.getBoardFile();
        String boardFileName = boardFile.getOriginalFilename();
        boardFileName = System.currentTimeMillis() + "_" + boardFileName;
        String savePath = "D:\\springboot_img\\" + boardFileName;
        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(savePath));
        }
        boardDTO.setBoardFileName(boardFileName);
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findByMemberEmail(boardDTO.getBoardWriter());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            Long savedId = boardRepository.save(BoardEntity.
                    toSaveEntity(boardDTO, memberEntity)).getId();
            return savedId;
        } else {
            return null;
        }

    }

    @Transactional
    public BoardDTO findById(Long id) {
        boardRepository.boardHits(id);
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toDTO(boardEntity);
        } else {
            return null;
        }
    }

    @Transactional
    public void update(BoardDTO boardDTO) throws IOException {
        BoardDTO findDTO = findById(boardDTO.getId());
        MultipartFile boardFile = boardDTO.getBoardFile();
        String boardFileName = boardFile.getOriginalFilename();

        if (!Objects.equals(findDTO.getBoardFileName(), boardDTO.getBoardFileName())) {
            if (!boardFile.isEmpty()) {
                boardFileName = System.currentTimeMillis() + "_" + boardFileName;
                String savePath = "D:\\springboot_img\\" + boardFileName;
                boardFile.transferTo(new File(savePath));
                boardDTO.setBoardFileName(boardFileName);
            } else {
                boardDTO.setBoardFileName(null);
            }
        } else if (findDTO.getBoardFileName() == null) {
            if (!boardFile.isEmpty()) {
                boardFileName = System.currentTimeMillis() + "_" + boardFileName;
                String savePath = "D:\\springboot_img\\" + boardFileName;
                boardFile.transferTo(new File(savePath));
                boardDTO.setBoardFileName(boardFileName);
            } else {
                boardDTO.setBoardFileName(null);
            }
        }

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(boardDTO.getBoardWriter());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO, memberEntity);

            boardRepository.save(boardEntity);
        }
    }

//    public void update(BoardDTO boardDTO) {
//        Optional<MemberEntity>optionalMemberEntity=
//                memberRepository.findByMemberEmail(boardDTO.getBoardWriter());
//        if(optionalMemberEntity.isPresent()){
//            MemberEntity memberEntity=optionalMemberEntity.get();
//        boardRepository.save(BoardEntity.toUpdateEntity(boardDTO,memberEntity));
//        }
//    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }


}
