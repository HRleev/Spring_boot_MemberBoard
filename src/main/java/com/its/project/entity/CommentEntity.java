package com.its.project.entity;

import com.its.project.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "commentBId")
    private Long commentBId;

    @Column(name = "commentWriter")
    private String commentWriter;

    @Column(name = "commentContents")
    private String commentContents;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO,BoardEntity boardEntity,MemberEntity memberEntity){
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setCommentBId(commentDTO.getCommentBId());
        commentEntity.setCommentWriter(memberEntity.getMemberEmail());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);
        return commentEntity;
    }
}
