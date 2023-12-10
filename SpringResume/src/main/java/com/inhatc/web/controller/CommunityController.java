package com.inhatc.web.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.web.config.auth.LoginUser;
import com.inhatc.web.dto.ResumePostCommentDto;
import com.inhatc.web.dto.ResumePostSearchDto;
import com.inhatc.web.dto.ResumePostWriteDto;
import com.inhatc.web.dto.SessionUser;
import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.MemberDetail;
import com.inhatc.web.entity.ResumeComment;
import com.inhatc.web.entity.ResumePost;
import com.inhatc.web.repository.MemberDetailRepository;
import com.inhatc.web.repository.MemberRepository;
import com.inhatc.web.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommunityController {

	private final MemberRepository memberRepository;
	
	private final MemberDetailRepository memberDetailRepository;
	
	private final PostService postService;
	
	@GetMapping("/resumenoticeboard/{page}")
	public String requestNoticeBoard(@PathVariable int page, Model model, @LoginUser SessionUser user, HttpSession session) {
		
		Page<ResumePost> paging = postService.resumePostPaging(page, "", "");

		model.addAttribute("paging", paging);
		model.addAttribute("resumePostSearchDto", new ResumePostSearchDto());
		if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	// 로그인 유저 정보
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
	            model.addAttribute("loginMemberId", loginMember.getId());
	            
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
		return "resumenoticeboard";
	}
	
	@PostMapping("/resumenoticeboard/{page}")
	public String requestSearchNoticeBoard(@PathVariable int page, @Valid ResumePostSearchDto resumePostSearchDto, Model model, @LoginUser SessionUser user, HttpSession session) {
		
		Page<ResumePost> paging = postService.resumePostPaging(page, resumePostSearchDto.getFilter(), resumePostSearchDto.getKeyword());

		model.addAttribute("paging", paging);
		model.addAttribute("resumePostSearchDto", new ResumePostSearchDto());
		
		if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	// 로그인 유저 정보
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
	            model.addAttribute("loginMemberId", loginMember.getId());
	            
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
		return "resumenoticeboard";
	}
	
	@PostMapping("/requestnoticeboard")
	public String requestSearchNoticeBoard(@RequestParam(value="page", defaultValue="0") int page, @RequestParam("keyword") String keyword, @RequestParam("filter") String filter, Model model, @LoginUser SessionUser user, HttpSession session) {
		
//		Page<RequestPost> paging = postService.requestPostPaging(page, filter, keyword);
//		
//		model.addAttribute("paging", paging);
		
		return "requestnoticeboard";
	}
	
	@GetMapping("/private/resumepostwrite")
	public String voicePostWrite(Model model, @LoginUser SessionUser user, HttpSession session) {
		
		if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	// 로그인 유저 정보
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
	            model.addAttribute("loginMemberId", loginMember.getId());
	            model.addAttribute("resumePostWriteDto", new ResumePostWriteDto());
//	            System.out.println("멤버디테일:" + memberDetail.getPictureUrl());

	         // 로그인유저 프로필 이미지 가져오기
	            if (loginMemberDetail.getPictureUrl().isEmpty()) {
	                model.addAttribute("loginPictureUrl", loginMember.getPictureUrl());
//	                System.out.println("url: " + member.getPictureUrl());
	            } else {
	                model.addAttribute("loginPictureUrl", loginMemberDetail.getPictureUrl());
	            }
	            
	        } else {
	        	
	        }
	    }
		return "resumepostwrite";
	}
	
	@PostMapping("/private/resumepostwrite")
	public String voicePostWrite(@Valid ResumePostWriteDto resumePostWriteDto, @RequestParam("resumeFileUpload") MultipartFile resumeFile, @LoginUser SessionUser user, Model model, HttpSession session) {
		
		try {
			  postService.saveResumePost(user, resumeFile, resumePostWriteDto);
      } catch (Exception e) {
          model.addAttribute("errorMessage", e.getMessage());
          return "resumepostwrite";
      }
		
		if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	// 로그인 유저 정보
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
	            model.addAttribute("loginMemberId", loginMember.getId());
//	            System.out.println("멤버디테일:" + memberDetail.getPictureUrl());

	         // 로그인유저 프로필 이미지 가져오기
	            if (loginMemberDetail.getPictureUrl().isEmpty()) {
	                model.addAttribute("loginPictureUrl", loginMember.getPictureUrl());
//	                System.out.println("url: " + member.getPictureUrl());
	            } else {
	                model.addAttribute("loginPictureUrl", loginMemberDetail.getPictureUrl());
	            }
	            
	        } else {
	        	
	        }
	    }
		
		
		Page<ResumePost> paging = postService.resumePostPaging(0, "", "");
		model.addAttribute("paging", paging);
		model.addAttribute("resumePostSearchDto", new ResumePostSearchDto());
		
		return "resumenoticeboard";
	}
	
	
	@GetMapping("/resumepost/{postId}/{page}")
	public String resumePost(@PathVariable long postId, @PathVariable int page, Model model, @LoginUser SessionUser user, HttpSession session) throws IOException {
		
		ResumePost resumePost = postService.getResumePost(postId);
		
		model.addAttribute("resumePost", resumePost);
//		
		Page<ResumeComment> resumeCommentList = postService.getResumeComment(postId, page);
//		
		model.addAttribute("comment", resumeCommentList);
//		
		postService.updateResumeView(postId); // 게시글 조회수 증가
		
		model.addAttribute("resumePostSearchDto", new ResumePostSearchDto());
		model.addAttribute("resumePostCommentDto", new ResumePostCommentDto());
		
		if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	// 로그인 유저 정보
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
	            model.addAttribute("loginMemberId", loginMember.getId());
	            
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
		return "resumepost";
	}
	
	@PostMapping("/private/resumecommentwrite/{postId}")
	public String requestCommentWrite(@PathVariable long postId, @Valid ResumePostCommentDto resumePostCommentDto, @LoginUser SessionUser user, Model model, HttpSession session) throws IOException {
		
		try {
			  postService.saveRequestComment(user, resumePostCommentDto, postId);
	      } catch (Exception e) {
	          model.addAttribute("errorMessage", e.getMessage());
	          return "resumepost";
	      }
		
		if (user != null) {
	        Optional<Member> optionalMember = memberRepository.findByLoginId(user.getLoginId());

	        if (optionalMember.isPresent()) {
	        	// 로그인 유저 정보
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
	            model.addAttribute("loginMemberId", loginMember.getId());
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
		
		ResumePost resumePost = postService.getResumePost(postId);
		
		model.addAttribute("resumePost", resumePost);
		
		Page<ResumeComment> resumeCommentList = postService.getResumeComment(postId, 0);
		
		model.addAttribute("comment", resumeCommentList);
		
		model.addAttribute("resumePostSearchDto", new ResumePostSearchDto());
		model.addAttribute("resumePostCommentDto", new ResumePostCommentDto());
		return "resumepost";
	}
	
	
	
	
}
