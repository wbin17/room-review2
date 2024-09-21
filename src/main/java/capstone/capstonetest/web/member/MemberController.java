package capstone.capstonetest.web.member;

import capstone.capstonetest.domain.board.Board;
import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.domain.member.MemberRepository;
import capstone.capstonetest.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
//    private final MemberRepository memberRepository;
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("member") Member member, Model model){

        if (memberService.isMemberIdAvailable(member.getMemberId())) {
            model.addAttribute("duplicateMessage", "이미 사용 중인 아이디입니다.");
            model.addAttribute("member", member);  // 입력된 회원 정보를 다시 전달
            return "members/addMemberForm";  // 다시 회원가입 페이지로 이동
        }

//        memberRepository.save(member);
        memberService.save(member);
        return "redirect:/";
    }

    @PostMapping("/checkDuplicate")
    public String checkDuplicate(@RequestParam String memberId, Model model) {
        boolean exists = memberService.isMemberIdAvailable(memberId);
        if (exists) {
            model.addAttribute("duplicateMessage", "이미 사용 중인 아이디입니다.");
            model.addAttribute("memberId", memberId);  // 입력된 회원 아이디를 다시 전달
//            return "members/addMemberForm";  // 회원가입 폼 페이지로 이동
        }
        model.addAttribute("memberId", memberId);  // 입력된 회원 아이디를 다시 전달
        return "/members/addMemberForm";
    }

    @GetMapping("/{memberId}")
    public String myPage(@PathVariable("memberId")String memberId,
                         Model model){
        Member member = memberService.myPage(memberId);
//        log.info("memberId = {}", member);
        for (Board post:
             member.getBoardList()) {
            log.info("board = {}", post);
        }
        model.addAttribute("member",member);
        return "/members/info";
    }

}
