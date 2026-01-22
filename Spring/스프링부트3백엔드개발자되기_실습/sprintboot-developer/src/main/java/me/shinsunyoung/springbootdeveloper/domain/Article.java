package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity //엔티티로 지정
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) //nullable = false : null값 허용 안함
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
    }

    protected Article() { //기본 생성자
    }

    //게터
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
