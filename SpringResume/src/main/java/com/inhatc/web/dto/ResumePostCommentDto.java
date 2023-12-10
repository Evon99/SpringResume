package com.inhatc.web.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.inhatc.web.entity.MemberDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumePostCommentDto {

	private Long id;
	
	//@NotBlank(message = "닉네임을 입력해주세요.")
//	@Length(min=2, message = "2글자 이상 입력해주세요.")
	private String title;
	
	private String comment;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ResumePostCommentDto of(MemberDetail memberImg) {
		return modelMapper.map(memberImg, ResumePostCommentDto.class);
	}
}
