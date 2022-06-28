package com.its.project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedDate;
    private MultipartFile boardFile;
    private String boardFileName;

    public BoardDTO(String boardTitle, String boardWriter, String boardContents, int boardHits, LocalDateTime boardCreatedDate, MultipartFile boardFile, String boardFileName) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.boardCreatedDate = boardCreatedDate;
        this.boardFile = boardFile;
        this.boardFileName = boardFileName;
    }
}
