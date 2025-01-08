package com.example.boardPractice.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 공통적으로 사용되는 컬럼이므로, 이를 상속한 클래스에서 컬럼을 추가한다.
 */


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class BaseTimeEntity {
    @Column(name="created_date",nullable = false)
    @CreatedDate
    private String createdDate;

    @Column(name="modified_date",nullable = false)
    @LastModifiedDate
    private String modifiedDate;

    // 해당 엔티티를 업데이트하기 이전에 실행하기
    @PrePersist
    public void onPrePersist(){
        this.createdDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
    // 해당 엔티티를 업데이트한 이후에 실행하기
    @PreUpdate
    public void onPreUpdate(){
        this.modifiedDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
