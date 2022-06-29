package com.its.project.dto;

import com.its.project.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedDate;
    private MultipartFile boardFile;
    private String boardFileName;

    public BoardDTO(String boardTitle, String boardWriter, String boardContents, int boardHits, MultipartFile boardFile, String boardFileName) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.boardFile = boardFile;
        this.boardFileName = boardFileName;
    }

    public BoardDTO(Long id, String boardTitle, String boardWriter, int boardHits, LocalDateTime boardCreatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardHits = boardHits;
        this.boardCreatedDate = boardCreatedDate;
    }

    public BoardDTO(String boardTitle, String boardWriter, String boardContents, int boardHits) {
        this.boardTitle=boardTitle;
        this.boardWriter=boardWriter;
        this.boardContents=boardContents;
        this.boardHits = boardHits;

    }


    public static BoardDTO toDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO=new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedDate(boardEntity.getCreatedTime());
        boardDTO.setBoardFileName(boardEntity.getBoardFileName());
        return boardDTO;
    }
}
