package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeActivity;
import com.inhatc.web.entity.ResumeForeign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeActivityFormDto{
	
    private Long id; //기본키
    
    private String activityName; // 활동명
    
//    private String activityDuration; // 활동 기간
    
    private String activityDurationStartYear; // 경험 기간 시작 년도
	
	private String activityDurationStartMonth; // 경험 기간 시작 월
	
	private String activityDurationStartDay; // 경험 기간 시작 일자

	private String activityDurationEndYear; // 경험 기간 끝 년도
	
	private String activityDurationEndMonth; // 경험 기간 끝 월
	
	private String activityDurationEndDay; // 경험 기간 끝 일자
    
    private String activityAgencyName; // 활동 기관명
    
    private String activityHistory; // 활동 내용
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeActivity createResumeActivity() {
    	return modelMapper.map(this, ResumeActivity.class);
    }
    
    public static List<ResumeActivityFormDto> listOf(List<ResumeActivity> resumeActivityList) {
        List<ResumeActivityFormDto> result = new ArrayList<>();

        for (ResumeActivity resumeActivity : resumeActivityList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeActivity, ResumeActivityFormDto> typeMap = modelMapper.getTypeMap(ResumeActivity.class, ResumeActivityFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeActivity.class, ResumeActivityFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeActivity, ResumeActivityFormDto.class));
        }

        return result;
    }
}
