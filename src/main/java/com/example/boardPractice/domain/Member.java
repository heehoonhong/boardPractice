package com.example.boardPractice.domain;


import com.example.boardPractice.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // 이름,비번,닉네임,이메일

    @Column(nullable = false,length=30,unique=true)
    private String memberName;

    @Column(nullable = false,length=30,unique=true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length=50,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /*회원 정보 수정*/
    public void modify(String nickname,String password){
        this.nickname = nickname;
        this.password = password;
    }

    /*
    * 소셜 로그인시 이미 등록된 회원이라면 수정 날짜만 업데이트해줘서
    * 기존 데이터를 보존하도록 예외처리
    * */
    public Member updateModifiedDate(){
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue(){
        return this.role.getValue();
    }
}
