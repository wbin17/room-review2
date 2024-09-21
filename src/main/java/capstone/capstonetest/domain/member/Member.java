package capstone.capstonetest.domain.member;

import capstone.capstonetest.domain.board.Board;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class Member {
    private Long id;

    private String memberId;
    private String memberPass;
    private String memberName;
    private String memberBirth;
    private String phone;
    private MemberRole memberRole = MemberRole.user;

    private List<Board> boardList;
}
