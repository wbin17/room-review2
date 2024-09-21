package capstone.capstonetest.web.board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDto {
    private String title;
    private String content;
    private Long roomId;
}
