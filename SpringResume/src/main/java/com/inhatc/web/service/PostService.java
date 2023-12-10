package com.inhatc.web.service;



import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.inhatc.web.config.auth.LoginUser;
import com.inhatc.web.dto.ResumePostCommentDto;
import com.inhatc.web.dto.ResumePostWriteDto;
import com.inhatc.web.dto.SessionUser;
import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.ResumeComment;
import com.inhatc.web.entity.ResumePost;
import com.inhatc.web.repository.MemberRepository;
import com.inhatc.web.repository.ResumeCommentRepository;
import com.inhatc.web.repository.ResumePostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

	@Value("${resumeFileLocation}")
	private String resumeFileLocation;
	
	private final MemberRepository memberRepository;
	
	private final ResumePostRepository resumePostRepository;
	
	private final ResumeCommentRepository resumeCommentRepository;
	
	private final FileService fileService;
	
	public Page<ResumePost> resumePostPaging(int page, String filter, String keyword) {
		Pageable pageable = PageRequest.of(page, 20);
		
		Page<ResumePost> resumePostList;
		
		if (filter.equals("title")) {
			 System.out.println("키워드 필터");
	         resumePostList = resumePostRepository.findAllByTitleOrderByRegTimeDescAndIdAsc(keyword, pageable);
	    } else if(filter.equals("nickname")) {
	    	 System.out.println("키워드:" + keyword);
	    	 resumePostList = resumePostRepository.findAllByNicknameContainingOrderByRegTimeDesc(keyword, pageable);
	    } else {
	    	resumePostList = resumePostRepository.findAllByOrderByRegTimeDescAndIdAsc(pageable);
	    }
		
		
		for (ResumePost post : resumePostList) {
			post.setUploaderNickname();
		}
		
		return resumePostList;
	}
	
	public void saveResumePost(@LoginUser SessionUser user, MultipartFile resumeFile, ResumePostWriteDto resumePostWriteDto) throws Exception {
			
			Member member = memberRepository.findByLoginId(user.getLoginId()).get();
			
			ResumePost resumePost = new ResumePost();
			resumePost.setMember(member);
			resumePost.setTitle(resumePostWriteDto.getTitle());
			resumePost.setContent(resumePostWriteDto.getContent().replace("\r\n","<br/>"));
			
			String oriResumeFileName = resumeFile.getOriginalFilename(); 
			String resumeFileName = ""; 
			String resumeFileUrl = "";
			
			if(!StringUtils.isEmpty(oriResumeFileName)){
				resumeFileName = fileService.uploadFile(resumeFileLocation, oriResumeFileName, resumeFile.getBytes());
				resumeFileUrl = "/resumefile/" + resumeFileName; // 파일 저장 장소
	        }
			
			resumePost.updateResumePost(resumeFileName, oriResumeFileName, resumeFileUrl);
			resumePostRepository.save(resumePost);		
			
		}
	

	public ResumePost getResumePost(long postId) throws IOException {

		ResumePost resumePost = resumePostRepository.findById(postId);
		
		resumePost.setUploaderImg();
		resumePost.setUploaderNickname();
		resumePost.setResumeFileSize(getResumeFileSize(resumePost.getResumeFileUrl()));
		return resumePost;
	}
	
	public String getResumeFileSize(String filePath) throws IOException {
		
		String realFilePath = "C:\\spring" + filePath;
		
		Path path = FileSystems.getDefault().getPath(realFilePath);

        // 파일 속성 가져오기
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);

        // 파일 크기 가져오기
        double fileSizeInMB = (double) attributes.size() / (1024 * 1024);
        String formattedFileSize = String.format("%.3f", fileSizeInMB);
        return formattedFileSize;
	}
	
	public Page<ResumeComment> getResumeComment(long postId, int page) {
		Pageable pageable = PageRequest.of(page, 20);
		
		Page<ResumeComment> ResumeCommentList = resumeCommentRepository.findByResumePost_IdOrderByRegTimeAsc(postId, pageable);
		
		for (ResumeComment comment : ResumeCommentList) {
			comment.setUploaderNickname();
			comment.setUploaderImg();
		}
		
		return ResumeCommentList;
	}
	
	public void saveRequestComment(@LoginUser SessionUser user, ResumePostCommentDto resumePostCommentDto, long postId) {
		
		Member member = memberRepository.findByLoginId(user.getLoginId()).get();
		ResumePost resumePost = resumePostRepository.findById(postId);
		
		ResumeComment resumeComment = new ResumeComment();
		resumeComment.setMember(member);
		resumeComment.setResumePost(resumePost);
		resumeComment.setComment(resumePostCommentDto.getComment().replace("\r\n","<br/>"));
		
		resumeCommentRepository.save(resumeComment);
		resumeCommentRepository.flush();
	}
	
	public void updateResumeView(long postId) {
		
		ResumePost resumePost = resumePostRepository.findById(postId);
		
		resumePost.setView(resumePost.getView() + 1);
		
		resumePostRepository.save(resumePost).getView();
	}

	public String getOriResumeFileName(String resumeFileName) {
		return resumePostRepository.findOriResumeFileNameByResumeFileName(resumeFileName);
	}
}
