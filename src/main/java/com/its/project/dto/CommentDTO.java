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
    private Long commentBId;
    private String commentWriter;

    private String commentContents;
    private LocalDateTime commentCreatedDate;

    public CommentDTO(Long commentBId, String commentWriter,String commentContents, LocalDateTime commentCreatedDate) {
        this.commentBId = commentBId;
        this.commentWriter = commentWriter;
        this.commentContents=commentContents;
        this.commentCreatedDate = commentCreatedDate;
    }

    public static CommentDTO toSaveDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentBId(commentEntity.getCommentBId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedDate(commentEntity.getCreatedTime());

        return commentDTO;
    }
}
