package capstone.capstonetest.domain.postComment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter @Getter
public class PostComment {
    private Long commentId;
    private String memberId;
    private Long postId;
    private String commentText;
    private Date commentDate;
}
