package capstone.capstonetest.web.postComment;

import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.domain.postComment.PostComment;
import capstone.capstonetest.service.PostCommentService;
import capstone.capstonetest.web.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
public class PostCommentController {
    private final PostCommentService postCommentService;

    @PostMapping("/add")
    public String addComment(@ModelAttribute("postComment")PostComment postComment){
        postCommentService.addComment(postComment);
        return "redirect:/posts/room/" + postComment.getPostId();
    }

    @PostMapping("/update")
    public String updateComment(@ModelAttribute("postComment")PostComment postComment,
                                HttpSession session,
                                Model model){
        Member loggedInMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (!loggedInMember.getMemberId().equals(postComment.getMemberId())) {
            model.addAttribute("error", "수정 권한이 없습니다.");
            return "error";
        }
        log.info("comm = {}", postComment);

        postCommentService.updateComment(postComment);
        return "redirect:/posts/room/" + postComment.getPostId();
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam("commentId")Long commentId,
                                @RequestParam("postId")Long postId){
        postCommentService.deleteComment(commentId);
        return "redirect:/posts/room/" + postId;
    }
}
