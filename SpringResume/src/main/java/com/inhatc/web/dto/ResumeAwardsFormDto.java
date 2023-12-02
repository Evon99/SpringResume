package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeAwards;
import com.inhatc.web.entity.ResumeCareer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeAwardsFormDto{
	
    private Long id; //기본키
    
    private String awardsName; // 수상명
    
//    private String awardsAcq; // 수상일
    
    private String awardsYear; // 수상 년도

	private String awardsMonth; // 수상 월
	
	private String awardsDay; // 수상 일자
    
    private String awardsAgencyName; // 수여기관명
    
    private String awardsHistory; // 수상내역
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeAwards createResumeAwards() {
    	return modelMapper.map(this, ResumeAwards.class);
    }
    
    public static List<ResumeAwardsFormDto> listOf(List<ResumeAwards> resumeAwardsList) {
        List<ResumeAwardsFormDto> result = new ArrayList<>();

        for (ResumeAwards resumeAwards : resumeAwardsList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeAwards, ResumeAwardsFormDto> typeMap = modelMapper.getTypeMap(ResumeAwards.class, ResumeAwardsFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeAwards.class, ResumeAwardsFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeAwards, ResumeAwardsFormDto.class));
        }

        return result;
    }
}
