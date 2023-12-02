package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.ResumeCareer;
import com.inhatc.web.entity.ResumeLanguage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeCareerFormDto {
	
    private Long id; //기본키
    
    private String companyName; // 회사명
    
    private String companyPosition; // 회사 직급 직책
    
    private String companyDepName; // 회사 부서명
    
    private String companyJob; // 회사 담당직무
    
//    private String companyDuration; // 회사 기간

    private String careerDurationStartYear; // 경력 기간 시작 년도
	
	private String careerDurationStartMonth; // 경력 기간 시작 월

	private String careerDurationEndYear; // 경력 기간 끝 년도
	
	private String careerDurationEndMonth; // 경력 기간 끝 월
	
    private String companyTenureDivision; // 회사 재직여부
    
    private String companyJobContent; // 회사 담당업무
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeCareer createResumeCareer() {
    	return modelMapper.map(this, ResumeCareer.class);
    }
    
    public static List<ResumeCareerFormDto> listOf(List<ResumeCareer> resumeCareerList) {
        List<ResumeCareerFormDto> result = new ArrayList<>();

        for (ResumeCareer resumeCareer : resumeCareerList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeCareer, ResumeCareerFormDto> typeMap = modelMapper.getTypeMap(ResumeCareer.class, ResumeCareerFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeCareer.class, ResumeCareerFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeCareer, ResumeCareerFormDto.class));
        }

        return result;
    }

}
