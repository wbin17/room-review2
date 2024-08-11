package capstone.capstonetest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Post {

    private Long id;
    private String title;
    private String content;
    private String writerName;
    private String address;
    //private String createdAt
    //db에서 꺼내올떼 date_format(createdAt, "%y-%m-%d") as createdAt
    //작성시간은 date 데이터형식으로 default now로 처리
    //조회수도 포함
    //private int boardHits;
    public Post() {
    }

    public Post(String title, String content, String writerName, String address) {
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.address = address;
    }
}
