package com.inhatc.web.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.ResumeHighSchool;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeHighSchoolFormDto {
	
    private Long id; //기본키
    
    private String highSchoolGraduateYear; // 고등학교 졸업년도
    
    private String highSchoolName; // 고등학교 이름
    
    private String highSchoolDivision; // 고등학교 졸업구분
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeHighSchool createResumeHighSchool() {
    	return modelMapper.map(this, ResumeHighSchool.class);
    }
    
    public static ResumeHighSchoolFormDto of(ResumeHighSchool resumeHighSchool) {
    	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    	return modelMapper.map(resumeHighSchool, ResumeHighSchoolFormDto.class);
    }
    
}
