package capstone.capstonetest.domain.board;

import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.domain.room.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class Board {
    private Long id;
    private String writerId;
    private Long roomId;
    private String title;
    private String content;
    private Date createdAt;

    private Room room;
    private Member member;
}
