package com.its.project.entity;

import javax.persistence.*;

@Entity
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "boardId")
    private Long boardId;

    @Column(name = "commentWriter")
    private String commentWriter;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberId")
//    private MemberEntity memberEntity;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "boardId")
//    private BoardEntity boardEntity;

}
