package com.inhatc.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inhatc.web.config.auth.LoginUser;
import com.inhatc.web.dto.ResumeCombinedFormDto;
import com.inhatc.web.dto.ResumePreferentFormDto;
import com.inhatc.web.dto.ResumeUniversityFormDto;
import com.inhatc.web.dto.SessionUser;
import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.MemberDetail;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.repository.MemberDetailRepository;
import com.inhatc.web.repository.MemberRepository;
import com.inhatc.web.service.MemberDetailService;
import com.inhatc.web.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResumeController {

	private final HttpSession httpSession;

	private final MemberDetailService MemberDetailService;

	private final MemberRepository memberRepository;

	private final MemberDetailRepository memberDetailRepository;
	
	private final ResumeService resumeService;

	@GetMapping("/resumelist")
	public String resumelist(Model model, @LoginUser SessionUser user, HttpSession session) {

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
	            
	            // 사용자가 작성한 이력서 리스트 가져오기 
	            List<Resume> resumeList = resumeService.getResumeList(loginMember.getId());
	            
	            model.addAttribute("resumeList", resumeList);
	           
	        } else {
	            // Optional이 비어 있다면 처리할 로직 추가
	        }
	    }
		return "resumelist";
	}

	@GetMapping("/resumecreate/{resumeId}")
	public String resumeCreate(@PathVariable Long resumeId, Model model, @LoginUser SessionUser user, HttpSession session) {
		
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
	            
	            ResumeCombinedFormDto resumeCombinedFormDto = resumeService.createCombinedFormDto(resumeId);
	            
	            model.addAttribute("resumeCombinedFormDto", resumeCombinedFormDto);
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
		
		return "resumecreate";
	}
	
	@PostMapping("/resumecreate")
	public String resumeNew(ResumeCombinedFormDto resumeCombinedFormDto, @RequestParam("resumeImgFile")MultipartFile resumeImgFile, Model model, @LoginUser SessionUser user, HttpSession session) {
		
		System.out.println("resumeCombinedFormDto: " + resumeCombinedFormDto.getResumeFormDto().getMemberName());
		List<ResumeUniversityFormDto> universityFormDtoList = resumeCombinedFormDto.getResumeUniversityFormDto();
		List<ResumePreferentFormDto> resumePreferentFormDtoList = resumeCombinedFormDto.getResumePreferentFormDto();
		
		// 이제 universityFormDtoList를 사용할 수 있음
//		for (ResumeUniversityFormDto universityFormDto : universityFormDtoList) {
//		    // ResumeUniversityFormDto의 각 요소에 접근
//		    System.out.println("대학교 이름:" + universityFormDto.getUniName());
//		    System.out.println("대학교 토탈 학점:" + universityFormDto.getUniTotalGrade());
//		    System.out.println("대학교 총 학점:" + universityFormDto.getUniMaxGrade());
//		    System.out.println("대학교 시작 연도:" + universityFormDto.getUniDurationStartYear());
//		    System.out.println("대학교 시작 월: " + universityFormDto.getUniDurationStartMonth());
//		    System.out.println("대학교 끝 연도:" + universityFormDto.getUniDurationEndYear());
//		    System.out.println("대학교 끝 월:" + universityFormDto.getUniDurationEndMonth());
//		    System.out.println("대학교 졸업 구분:" + universityFormDto.getUniGraduateDivison());
//		    System.out.println("대학교 전곰명:" + universityFormDto.getUniMajor());
//		    System.out.println("대학교 분류:" + universityFormDto.getUniDivision());
//		}
		
//		for(ResumePreferentFormDto resumePreferentFormDto : resumePreferentFormDtoList) {
//			System.out.println("보훈사항:" + resumePreferentFormDto.getVeteransDivison());
//			System.out.println("군별:" + resumePreferentFormDto.getByMilitaryDuration());
//			System.out.println("장애여부:" + resumePreferentFormDto.getObstacleDivsion());
//			System.out.println("군필여부:" + resumePreferentFormDto.getMilitaryDivison());
//		}
		try {
			System.out.println("이력서ID:" + resumeCombinedFormDto.getResumeFormDto().getId());
			if(resumeCombinedFormDto.getResumeFormDto().getId() == null) {
				resumeService.createResume(resumeCombinedFormDto, resumeImgFile, user);
			} else {
				resumeService.updateResume(resumeCombinedFormDto, resumeImgFile, user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	            
	            // 사용자가 작성한 이력서 리스트 가져오기 
	            List<Resume> resumeList = resumeService.getResumeList(loginMember.getId());
	            
	            model.addAttribute("resumeList", resumeList);
	           
	        } else {
	            // Optional이 비어 있다면 처리할 로직 추가
	        }
	    }
		
		return "resumelist";
	}
	
	@GetMapping("/resumedelete/{resumeId}")
	public String resumedelete(@PathVariable Long resumeId, Model model, @LoginUser SessionUser user, HttpSession session) {
		
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
	            resumeService.deleteResume(resumeId);
	            
	            // 사용자가 작성한 이력서 리스트 가져오기 
	            List<Resume> resumeList = resumeService.getResumeList(loginMember.getId());
	            
	            model.addAttribute("resumeList", resumeList);
	           
	        } else {
	            // Optional이 비어 있다면 처리할 로직 추가
	        }
	    }
		return "resumelist";
	}
	
	@GetMapping("/resumeview/{resumeId}")
	public String resumeView(@PathVariable Long resumeId, Model model, @LoginUser SessionUser user, HttpSession session) {
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
	            // 사용자가 작성한 이력서 리스트 가져오기 
	            List<Resume> resumeList = resumeService.getResumeList(loginMember.getId());
	            
	            model.addAttribute("resumeList", resumeList);
	           
	            ResumeCombinedFormDto resumeCombinedFormDto = resumeService.createCombinedFormDto(resumeId);
	    		
	    		model.addAttribute("resumeCombinedFormDto", resumeCombinedFormDto);
	        } else {
	            // Optional이 비어 있다면 처리할 로직 추가
	        }
	    }
		
		

		return "resumeviewtest";
	}
}