package com.inhatc.web.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.inhatc.web.entity.MemberDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumePostSearchDto {

	private String filter;
	
	private String keyword;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ResumePostSearchDto of(MemberDetail memberImg) {
		return modelMapper.map(memberImg, ResumePostSearchDto.class);
	}
}
