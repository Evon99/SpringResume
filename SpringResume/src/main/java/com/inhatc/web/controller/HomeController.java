package com.inhatc.web.controller;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.web.config.auth.LoginUser;
import com.inhatc.web.dto.MemberDetailDto;
import com.inhatc.web.dto.SessionUser;
import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.MemberDetail;
import com.inhatc.web.repository.MemberDetailRepository;
import com.inhatc.web.repository.MemberRepository;
import com.inhatc.web.service.MemberDetailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final HttpSession httpSession;
	
	private final MemberDetailService MemberDetailService;
	
	private final MemberRepository memberRepository;
	
	private final MemberDetailRepository memberDetailRepository;
	
	@GetMapping("/")
	public String home(Model model, @LoginUser SessionUser user, HttpSession session) {
		
	    if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	Member loginMember = optionalMember.get();
	            MemberDetail loginMemberDetail = memberDetailRepository.findByMember_Id(loginMember.getId());
	            
	            
	            // Check if memberDetail is null
	            if (loginMemberDetail == null || loginMemberDetail.getNickname() == null) {
	                // Clear the session
	                session.invalidate();
	                // Redirect to the home page
	                return "redirect:/";
	            }
	            
	            model.addAttribute("session", user);
	            model.addAttribute("loginNickname", loginMemberDetail.getNickname());

//	            System.out.println("멤버디테일:" + memberDetail.getPictureUrl());

	         // 로그인유저 프로필 이미지 가져오기
	            if (loginMemberDetail.getPictureUrl().isEmpty()) {
	                model.addAttribute("loginPictureUrl", loginMember.getPictureUrl());
//	                System.out.println("url: " + member.getPictureUrl());
	            } else {
	                model.addAttribute("loginPictureUrl", loginMemberDetail.getPictureUrl());
	            }
	            
	        } else {
	            // Optional이 비어 있다면 처리할 로직 추가
	        }
	    }
	    return "index";
	}
	
	@GetMapping("/private/profileSetting")
	public String createMember(Model model, @LoginUser SessionUser user) {
		
		Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());
		Member member = optionalMember.get();
        MemberDetail memberDetail = memberDetailRepository.findByMember_Id(member.getId());

        if(memberDetail == null || memberDetail.getNickname() == null) {
		
	        if(user != null){
	            model.addAttribute("memberLoginId", user.getLoginId());
	            model.addAttribute("memberName", user.getName());
	            model.addAttribute("memberPicture", user.getPictureUrl());
	        }
	        model.addAttribute("memberDetailDto", new MemberDetailDto());
			return "createMember";
        } else {
        	return "redirect:/";
        }
	}
	
	@PostMapping("/private/profileSetting")
	public String memberNew(@Valid MemberDetailDto memberDetailDto, BindingResult bindingResult, Model model, @RequestParam("memberImgFile")MultipartFile memberImgFile, @LoginUser SessionUser user) {
		
		System.out.println("postMapping");
		
		if(bindingResult.hasErrors()) { 
			model.addAttribute("memberLoginId", user.getLoginId());
	        model.addAttribute("memberName", user.getName());
	        model.addAttribute("memberPicture", user.getPictureUrl());
            return "createMember";
        }
		
		
		try {
            MemberDetailService.savePictureFile(memberImgFile, memberDetailDto.getNickname());
        } catch (Exception e) {
            
            model.addAttribute("memberLoginId", user.getLoginId());
            model.addAttribute("memberName", user.getName());
            model.addAttribute("memberPicture", user.getPictureUrl());
            
            model.addAttribute("errorMessage", e.getMessage());
            return "createMember";
        }
		
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logoutv2(HttpServletRequest request) {
		//세션을 삭제
		HttpSession session = request.getSession(false); 
        // session이 null이 아니라는건 기존에 세션이 존재했었다는 뜻이므로
        // 세션이 null이 아니라면 session.invalidate()로 세션 삭제해주기.
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
}
