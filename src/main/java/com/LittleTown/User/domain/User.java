package com.LittleTown.User.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long useridx;

    @Column(length=30, nullable = false, unique = true)
    private String id;

    @Column(length=30, nullable = false)
    private String pw;

    @Column(length=20, nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Long clothes;

    @Column(nullable = false)
    private Long money;

    @Builder
    public User(String id, String pw, String nickname, Long clothes, Long money) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.clothes = clothes;
        this.money = money;
    }
}