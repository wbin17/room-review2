package capstone.capstonetest.web.board;

//import capstone.capstonetest.domain.board.Board;
import capstone.capstonetest.GeocodingService;
import capstone.capstonetest.domain.board.Board;
import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.domain.postComment.PostComment;
import capstone.capstonetest.domain.room.Room;
import capstone.capstonetest.service.BoardService;
import capstone.capstonetest.service.PostCommentService;
import capstone.capstonetest.service.RoomService;
import capstone.capstonetest.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class BoardController {
    private final BoardService boardService;
    private final RoomService roomService;
    private final GeocodingService geocodingService;
    private final PostCommentService postCommentService;

    @GetMapping("/{roomId}")
    public String RoomReview(@PathVariable("roomId")Long roomId,
                             Model model){
        log.info("roomId = {}", roomId);
        List<Board> reviews = boardService.showReviews(roomId);
        log.info("reviews = {}", reviews);
        model.addAttribute("roomId", roomId);
        model.addAttribute("reviews", reviews);
        return "/board/roomId-review";
    }

    @GetMapping
    public String posts(Model model){
        List<Board> posts = boardService.findAll();
        model.addAttribute("posts", posts);
        return "/board/posts";
    }
    @GetMapping("/add")
    public String addForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
        return "/board/addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("board")Board board,
                       @RequestParam("address")String address,
                       RedirectAttributes redirectAttributes,
                       HttpServletRequest request,
                       Model model){
        if(roomService.findIdByAddress(address)==null) {
            try {
                double[] coordinates = geocodingService.getCoordinatesFromAddress(address);

                //Room객체 생성 및 저장
                Room room = new Room();
                room.setAddress(address);
                room.setLatitude(coordinates[0]);
                room.setLongitude(coordinates[1]);

                Long roomId = roomService.addRoom(room);
                board.setRoomId(roomId);
            } catch (IOException e) {
                model.addAttribute("error", "주소 변환에 실패했습니다.");
                return "error";
            }
        }
        else {
            board.setRoomId(roomService.findIdByAddress(address));
        }


      //  board.setRoomId(roomId);
        Long postId = boardService.save(board);

        redirectAttributes.addAttribute("postId", postId);

        return "redirect:/posts/room/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable("postId")Long postId,Model model){
        Board post = boardService.findById(postId);
        model.addAttribute("post",post);
        return "/board/editForm";

    }

    @PostMapping("/{postId}/edit")
    public String editPost(
                           @ModelAttribute("post") Board post){
        boardService.editPost(post);
        return "redirect:/posts/room/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable("postId")Long postId){
        boardService.deletePost(postId);
        return "redirect:/map";
    }
//    @PostMapping("/add")
//    public String save(@ModelAttribute("post") Post post){
//        boardService.save(post);
//        return "/board/posts";
//    }

//    @GetMapping("/{postId}/edit")
//    public String editForm(@PathVariable Long postId, Model model){
//        Post post = boardService.findById(postId);
//        model.addAttribute("post", post);
//        return "/board/editForm";
//    }
//
//    @PostMapping("/{postId}/edit")
//    public String edit(@PathVariable Long postId, @ModelAttribute("post") Post post, Model model){
//        boardService.update(post);
//        Post byId = boardService.findById(postId);
//        model.addAttribute("post", byId);
//        return "redirect:/posts/{postId}";
//    }
//
//    @GetMapping("/{postId}/delete")
//    public String delete(@PathVariable Long postId){
//
//        boardService.delete(postId);
//        return "redirect:/posts";
//    }
//
    @GetMapping("/room/{postId}")
    public String post(@PathVariable("postId")Long postId,
                       HttpServletRequest request,
                       Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        model.addAttribute("loginMember", loginMember);
        //조회수 처리
//        boardService.updateHits(postId)
        Board savedPost = boardService.findBoardDetailById(postId);
        model.addAttribute("post", savedPost);

        List<PostComment> comments = postCommentService.findCommentByPostId(postId);
        model.addAttribute("comments", comments);

//        Post post = postRepository.findById(postId);
//        model.addAttribute("post", post);
        return "/board/post";
    }
}
