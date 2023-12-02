package com.inhatc.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeCombinedFormDto {
	
    private ResumeFormDto resumeFormDto;
    
    private ResumeHighSchoolFormDto resumeHighSchoolFormDto;
    
    private List<ResumeUniversityFormDto> resumeUniversityFormDto;
    
    private List<ResumeGraduateSchoolFormDto> resumeGraduateSchoolFormDto;
    
    private List<ResumeCareerFormDto> resumeCareerFormDto;
    
    private List<ResumeLanguageFormDto> resumeLanguageFormDto;
    
    private List<ResumeAwardsFormDto> resumeAwardsFormDto;
    
    private List<ResumeForeignFormDto> resumeForeignFormDto;
    
    private List<ResumeActivityFormDto> resumeActivityFormDto;
    
    private List<ResumeEducationFormDto> resumeEducationFormDto;
    
    private List<ResumePreferentFormDto> resumePreferentFormDto;
    
//    private ResumeValueFormDto resumeValueFormDto;
    
}
