package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeEducation;
import com.inhatc.web.entity.ResumePreferent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumePreferentFormDto {
	
    private Long id; //기본키
    
    private String veteransDivison; // 보훈사항
    
    private String obstacleDivsion; // 장애여부
    
    private String obstacleGrade; // 장애등급
    
    private String militaryDivison; // 병역구분
    
//    private String militaryDuration; // 복무기간
    
    private String militaryDurationStartYear; // 복무 기간 시작 년도
	
	private String militaryDurationStartMonth; // 복무 기간 시작 월

	private String militaryDurationEndYear; // 복무 기간 끝 년도
	
	private String militaryDurationEndMonth; // 복무 기간 끝 월
    
    private String byMilitaryDuration; // 군별
    
    private String MilitaryClasses; // 군 계급
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumePreferent createResumePreferent() {
    	return modelMapper.map(this, ResumePreferent.class);
    }
    
    public static List<ResumePreferentFormDto> listOf(List<ResumePreferent> resumePreferentList) {
        List<ResumePreferentFormDto> result = new ArrayList<>();

        for (ResumePreferent resumePreferent : resumePreferentList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumePreferent, ResumePreferentFormDto> typeMap = modelMapper.getTypeMap(ResumePreferent.class, ResumePreferentFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumePreferent.class, ResumePreferentFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumePreferent, ResumePreferentFormDto.class));
        }

        return result;
    }
    
}
