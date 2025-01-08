package com.example.boardPractice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목 내용 작성자 조회수

    @Column(nullable = false,length=500)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false,columnDefinition = "integer default 0")
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy="post",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
    private List<Comment> comments;

    // 게시글 수정
    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }

}
