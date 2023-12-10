package com.inhatc.web.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.inhatc.web.entity.MemberDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumePostWriteDto {

	private Long id;
	
	private String title;
	
	private String content;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ResumePostWriteDto of(MemberDetail memberImg) {
		return modelMapper.map(memberImg, ResumePostWriteDto.class);
	}
}
