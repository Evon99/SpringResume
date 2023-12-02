package com.inhatc.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import com.inhatc.web.entity.Member;
import com.inhatc.web.entity.Resume;
import com.inhatc.web.entity.ResumeLanguage;
import com.inhatc.web.entity.ResumeUniversity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeLanguageFormDto {
	
    private Long id; //기본키
    
    private String languageName; // 언어명
    
//    private String languageGrade; // 언어 점수/급수
//    
//    private String languageAcq; // 언어 취득일
    
    private String languageTotalScore; // 언어 점수
	 
	private String languageRating; // 언어 급수 
	
	private String languageAcqYear; // 언어 취득 년도

	private String languageAcqMonth; // 언어 취득 월
	
	private String languageAcqDay; // 언어 취득 일자
    
    private String languageTestName; // 언어 시험명
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public ResumeLanguage createResumeLanguage() {
    	return modelMapper.map(this, ResumeLanguage.class);
    }
    
    public static List<ResumeLanguageFormDto> listOf(List<ResumeLanguage> resumeLanguageList) {
        List<ResumeLanguageFormDto> result = new ArrayList<>();

        for (ResumeLanguage resumeLanguage : resumeLanguageList) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<ResumeLanguage, ResumeLanguageFormDto> typeMap = modelMapper.getTypeMap(ResumeLanguage.class, ResumeLanguageFormDto.class);

            if (typeMap == null) {
                typeMap = modelMapper.createTypeMap(ResumeLanguage.class, ResumeLanguageFormDto.class);
                typeMap.addMappings(mapper -> {
                    // 추가 필요한 매핑이 있다면 여기에 추가
                });
            }

            result.add(modelMapper.map(resumeLanguage, ResumeLanguageFormDto.class));
        }

        return result;
    }
    
    
}
