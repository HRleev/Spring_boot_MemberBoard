package com.its.project.dto;

import com.its.project.entity.BaseEntity;
import com.its.project.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO extends BaseEntity {
    private Long id;
    private String boardId;
    private String commentWriter;

    private String commentContents;
    private LocalDateTime commentCreatedDate;

    public CommentDTO(String boardId, String commentWriter,String commentContents, LocalDateTime commentCreatedDate) {
        this.boardId = boardId;
        this.commentWriter = commentWriter;
        this.commentContents=commentContents;
        this.commentCreatedDate = commentCreatedDate;
    }

    public static CommentDTO toSaveDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedDate(commentEntity.getCreatedTime());

        return commentDTO;
    }
}
