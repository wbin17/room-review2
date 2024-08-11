package capstone.capstonetest;

import capstone.capstonetest.Repository.PostMemoryRepository;
import capstone.capstonetest.domain.Post;
import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.service.BoardService;
import capstone.capstonetest.web.SessionConst;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class BoardController {

    private final PostMemoryRepository postRepository;
    private final BoardService boardService;
    @GetMapping
    public String posts(Model model){
        List<Post> posts = boardService.findAll();
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
    public String save(@ModelAttribute("post") Post post ,
                       RedirectAttributes redirectAttributes,
                       HttpServletRequest request,
                       Model model){
        boardService.save(post);
        Post id = boardService.findByContent(post);

        redirectAttributes.addAttribute("postId", id.getId());

        return "redirect:/posts/{postId}";
    }
//    @PostMapping("/add")
//    public String save(@ModelAttribute("post") Post post){
//        boardService.save(post);
//        return "/board/posts";
//    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model){
        Post post = boardService.findById(postId);
        model.addAttribute("post", post);
        return "/board/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @ModelAttribute("post") Post post, Model model){
        boardService.update(post);
        Post byId = boardService.findById(postId);
        model.addAttribute("post", byId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId){

        boardService.delete(postId);
        return "redirect:/posts";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable("postId") Long postId, Model model){
        //조회수 처리
//        boardService.updateHits(postId)
        Post savedPost = boardService.findById(postId);
        model.addAttribute("post", savedPost);

//        Post post = postRepository.findById(postId);
//        model.addAttribute("post", post);
        return "/board/post";
    }



    @PostConstruct
    public void init(){
        postRepository.save(new Post("원룸1", "원룸1은 너무 별로에요", "박원빈", "천안시 서북구"));
        postRepository.save(new Post("원룸2", "원룸2는 좋아요!!", "이민수", "서울시 강남구"));
    }
}
