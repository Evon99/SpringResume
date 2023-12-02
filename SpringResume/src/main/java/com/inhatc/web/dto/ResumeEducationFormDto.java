package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumeEducation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeEducationFormDto {
	
    private Long id; //기본키
    
    private String eduName; // 교육명
    
//    private String eduDuration; // 교육 기간

    private String eduDurationStartYear; // 교육 기간 시작 년도
	
	private String eduDurationStartMonth; // 교육 기간 시작 월
	
	private String eduDurationStartDay; // 교육 기간 시작 일자

	private String eduDurationEndYear; // 교육 기간 끝 년도
	
	private String eduDurationEndMonth; // 교육 기간 끝 월

	private String eduDurationEndDay; // 교육 기간 끝 일자
	
    private String eduAgencyName; // 교육 기관명
    
    private String eduHistory; // 교육 내용
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeEducation createResumeEducation() {
    	return modelMapper.map(this, ResumeEducation.class);
    }
    
    public static List<ResumeEducationFormDto> listOf(List<ResumeEducation> resumeEducationList) {
        List<ResumeEducationFormDto> result = new ArrayList<>();

        for (ResumeEducation resumeEducation : resumeEducationList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeEducation, ResumeEducationFormDto> typeMap = modelMapper.getTypeMap(ResumeEducation.class, ResumeEducationFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeEducation.class, ResumeEducationFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeEducation, ResumeEducationFormDto.class));
        }

        return result;
    }
}
