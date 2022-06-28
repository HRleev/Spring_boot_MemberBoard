package com.its.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "boardWriter")
    private String boardWriter;

    @Column(name = "boardContents")
    private String boardContents;

    @Column(name = "boardHits")
    private int boardHits;

    @Column(name = "boardFileName")
    private String boardFileName;


//    게시글 작성자 회원아이디 참조
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberId")
//    private MemberEntity memberEntity;

}
