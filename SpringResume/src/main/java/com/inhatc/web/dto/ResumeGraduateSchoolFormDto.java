package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.ResumeGraduateSchool;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeGraduateSchoolFormDto {
	
    private Long id; //기본키
    
    private String gradSchoolName; // 대학원 이름
    
//    private String gradSchoolGrade; // 대학원 학점
//    
//    private String gradSchoolDuration; // 대학원 기간
    
    private String gradTotalGrade; // 대학원 학점
	 
	private String gradMaxGrade; // 대학원 최대 학점 
	
	private String gradDurationStartYear; // 대학원 기간 시작 년도
	
	private String gradDurationStartMonth; // 대학원 기간 시작 월

	private String gradDurationEndYear; // 대학원 기간 끝 년도
	
	private String gradDurationEndMonth; // 대학원 기간 끝 월
    
    private String gradSchoolGraduateDivison; // 대학원 졸업 구분
    
    private String gradSchoolMajor; // 대학원 전공명
    
    private String gradSchoolDivision; // 대학원 분류
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeGraduateSchool createResumeGraduateSchool() {
    	return modelMapper.map(this, ResumeGraduateSchool.class);
    }
    
    public static List<ResumeGraduateSchoolFormDto> listOf(List<ResumeGraduateSchool> graduateSchoolList) {
        List<ResumeGraduateSchoolFormDto> result = new ArrayList<>();

        for (ResumeGraduateSchool graduateSchool : graduateSchoolList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeGraduateSchool, ResumeGraduateSchoolFormDto> typeMap = modelMapper.getTypeMap(ResumeGraduateSchool.class, ResumeGraduateSchoolFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeGraduateSchool.class, ResumeGraduateSchoolFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(graduateSchool, ResumeGraduateSchoolFormDto.class));
        }

        return result;
    }

}
