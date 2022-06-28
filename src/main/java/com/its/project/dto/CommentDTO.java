package com.its.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String boardId;
    private String commentWriter;
    private LocalDateTime commentCreatedDate;

    public CommentDTO(String boardId, String commentWriter, LocalDateTime commentCreatedDate) {
        this.boardId = boardId;
        this.commentWriter = commentWriter;
        this.commentCreatedDate = commentCreatedDate;
    }
}
