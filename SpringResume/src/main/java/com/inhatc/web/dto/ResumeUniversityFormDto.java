package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.ResumeUniversity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeUniversityFormDto {
	
    private Long id; //기본키
    
    private String uniName; // 대학교 이름
    
    private String uniTotalGrade; // 대학교 총 학점
	 
	private String uniMaxGrade; // 대학교 최대 학점 
	
	private String uniDurationStartYear; // 대학교 기간 시작 년도
	
	private String uniDurationStartMonth; // 대학교 기간 시작 월

	private String uniDurationEndYear; // 대학교 기간 끝 년도
	
	private String uniDurationEndMonth; // 대학교 기간 끝 월
	
//    private String uniGrade; // 대학교 학점
    
//    private String uniDuration; // 대학교 기간
    
    private String uniGraduateDivison; // 대학교 졸업 구분
    
    private String uniMajor; // 대학교 전공명
    
    private String uniDivision; // 대학교 분류
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeUniversity createResumeUniversity() {
    	return modelMapper.map(this, ResumeUniversity.class);
    }
    
    public static List<ResumeUniversityFormDto> listOf(List<ResumeUniversity> resumeUniversityList) {
        List<ResumeUniversityFormDto> result = new ArrayList<>();

        for (ResumeUniversity resumeUniversity : resumeUniversityList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeUniversity, ResumeUniversityFormDto> typeMap = modelMapper.getTypeMap(ResumeUniversity.class, ResumeUniversityFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeUniversity.class, ResumeUniversityFormDto.class);
                typeMap.addMappings(mapper -> {
                    mapper.map(src -> src.getUniName(), ResumeUniversityFormDto::setUniName);
                    mapper.map(src -> src.getUniDivision(), ResumeUniversityFormDto::setUniDivision);
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeUniversity, ResumeUniversityFormDto.class));
        }

        return result;
    }

}
