package enggaarden.app.controllers;

import enggaarden.app.models.Factories.MemberFactory;
import enggaarden.app.models.Entities.Member;
import enggaarden.app.models.Entities.MemberType;
import enggaarden.app.models.interfaces.MemberRepositoryInterface;
import enggaarden.app.models.interfaces.SubLogRepositoryInterface;
import enggaarden.app.models.repositories.MemberRepository;
import enggaarden.app.models.repositories.SubLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MemberController
{
    /*
    Fields
     */
    @Autowired
    private MemberRepositoryInterface memberRepository = new MemberRepository();
    @Autowired
    private SubLogRepositoryInterface subLogRepository = new SubLogRepository();
    private MemberFactory memberFactory = new MemberFactory();

    /*
    Overview Methods
     */
    @GetMapping("/members")
    public String overview(Model model)
    {
        model.addAttribute("count", memberFactory.getMembers(memberRepository.getMember()).size());
        model.addAttribute("members", memberFactory.getMembers(memberRepository.getMember()));
        return "/Members/members_overview";
    }

    @GetMapping("/details")
    public String details(@RequestParam("memberId") int id, Model model)
    {
        model.addAttribute("member", memberFactory.getMember(memberRepository.getMember(id)));
        return "/Members/member_details";
    }

    /*
    Create Methods
     */
    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("boardoptions", memberFactory.getBoardOptions());
        model.addAttribute("member", memberFactory.createMember());
        model.addAttribute("memberTypes", memberRepository.getMemberTypes());
        return "/Members/member_create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Member member)
    {
        memberRepository.postMember(member);
        return "redirect:/members";
    }

    /*
    Edit Methods
     */
    @GetMapping("/edit")
    public String edit(@RequestParam("memberId") int id, Model model)
    {
        Member member = memberFactory.getMember(memberRepository.getMember(id));
        model.addAttribute("member", member);
        model.addAttribute("boardoptions", memberFactory.getBoardOptions());
        model.addAttribute("subscription", member.getSubscription());
        model.addAttribute("memberTypes", memberRepository.getMemberTypes());
        return "/Members/member_edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Member member, @ModelAttribute Date date, Principal principal)
    {
        memberRepository.updateMember(member);
        subLogRepository.post(date, member, principal.getName());

        return "redirect:/members";
    }


    /*
    Delete Methods
     */
    @GetMapping("/delete")
    public String deleteMember(@RequestParam("memberId") int id, Model model)
    {
        model.addAttribute("member", memberFactory.getMember(memberRepository.getMember(id)));
        model.addAttribute("memberType", MemberType.values());
        return "/Members/member_delete";
    }

    @PostMapping("/delete")
    public String deleteMember(@RequestParam("memberId") int id)
    {
        memberRepository.deleteMember(id);
        return "redirect:/members";
    }

    /*
    Reset Subscription Methods
     */
    @GetMapping("/reset")
    public String reset()
    {
        return "/Members/member_subreset";
    }

    @PostMapping("/reset")
    public String resetSub()
    {
        memberRepository.resetSubscriptions();
        return "redirect:/members";
    }
}
